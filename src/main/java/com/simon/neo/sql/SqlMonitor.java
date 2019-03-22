package com.simon.neo.sql;

import com.simon.neo.Neo;
import com.simon.neo.NeoMap;
import com.simon.neo.util.TimeStrUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * sql的监控
 *
 * @author zhouzhenyong
 * @since 2019/3/22 下午1:38
 */
@Slf4j
public class SqlMonitor {

    private static final String PRE_LOG = "[Neo-monitor] ";
    private static final Integer ONE_MILLION = 1;
    private static final Integer ONE_SECOND = 1000 * ONE_MILLION;
    private static final Integer THREE_SECOND = 3 * ONE_SECOND;
    private static final Integer TEN_SECOND = 10 * ONE_SECOND;
    private static final Integer ONE_MINUTE = 60 * ONE_SECOND;
    /**
     * 默认的sql语句打印的最大长度
     */
    private static final Integer MAX_SQL_LENGTH = 500;
    /**
     * 是否启动sql语句的explain功能
     */
    private boolean explainFlag = true;
    /**
     * 本地时间设置
     */
    private ThreadLocal<SqlCost> sqlTime = new ThreadLocal<>();
    private static SqlMonitor instance = new SqlMonitor();

    private SqlMonitor() {
    }

    public static SqlMonitor getInstance() {
        return instance;
    }

    public void start(Neo neo, String sql, List<Object> paramsList) {
        sqlTime.set(new SqlCost(System.currentTimeMillis(), sql, paramsList));
    }

    /**
     * 普通返回debug；超过3s则Info打印；超过10s则warn打印；超过1分钟则error打印
     */
    public void calculate() {
        SqlCost cost = sqlTime.get();
        Long value = System.currentTimeMillis() - cost.getStartTime();
        // 超时1分钟，上报重大告警
        if (value > ONE_MINUTE) {
            log.error(PRE_LOG + cost.buildCost(value));
            return;
        }

        // 超时10秒，上报普通告警
        if (value > TEN_SECOND) {
            log.warn(PRE_LOG + cost.buildCost(value));
            return;
        }

        // 超时3秒，上报info日志
        if (value > THREE_SECOND) {
            log.info(PRE_LOG + cost.buildCost(value));
            return;
        }

        log.debug(PRE_LOG + cost.buildCost(value));
    }

    /**
     * 请将该函数放到finally中，用于释放ThreadLocal中的数据，防止线程复用时候的数据残留造成异常
     */
    public void close() {
        sqlTime.remove();
    }

    /**
     * sql耗时实体
     */
    @Setter
    @AllArgsConstructor
    class SqlCost {

        @Getter
        private Long startTime;
        private String sql;
        private List<Object> paramsList;

        private String getSql() {
            if (sql.length() <= MAX_SQL_LENGTH) {
                return sql;
            }
            log.warn("sql 长度过长超过500");
            return sql.substring(0, MAX_SQL_LENGTH) + " ...";
        }

        String buildCost(Long costTime){
            return "[耗时: " + TimeStrUtil.parseTime(costTime) + "] [sql => " + getSql() + "], {params => " + paramsList + " }";
        }
    }
}