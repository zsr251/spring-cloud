package com.jc.base.datasource;

/**
 * 切换数据源Advice
 * Created by jasonzhu on 2017/9/30.
 */
//@Aspect
//@Order(-1)// 保证该AOP在@Transactional之前执行
//@Component
//@Slf4j
public class DynamicDataSourceAspect {
//    @Before("@annotation(ds)")
//    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
//        String dsId = ds.name();
//        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
//            log.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
//        } else {
//            log.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
//            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
//        }
//    }
//
//    @After("@annotation(ds)")
//    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
//        log.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
//        DynamicDataSourceContextHolder.clearDataSourceType();
//    }
}
