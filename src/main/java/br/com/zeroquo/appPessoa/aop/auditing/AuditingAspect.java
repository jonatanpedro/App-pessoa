package br.com.zeroquo.appPessoa.aop.auditing;

import br.com.zeroquo.appPessoa.domain.Auditable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.lang.reflect.Method;
import java.time.ZonedDateTime;

@Aspect
public class AuditingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public AuditingAspect(Environment env) {
        this.env = env;
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(br.com.zeroquo.appPessoa.service..*)")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut() && springBeanPointcut()")
    public void beforeSaveOrUpdate(JoinPoint joinPoint){
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            if(method.getName().startsWith("save")){
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    if (arg instanceof Auditable){
                        Auditable auditable = (Auditable)arg;
                        auditable.setCreationTime(ZonedDateTime.now());
                    }
                }
            }else if(method.getName().startsWith("update")){
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    if (arg instanceof Auditable){
                        Auditable auditable = (Auditable)arg;
                        auditable.setModificationTime(ZonedDateTime.now());
                    }
                }
            }
        } catch (Exception e) {
            log.error("AuditAspect Fail on set audit properties", e);
        }
    }
}
