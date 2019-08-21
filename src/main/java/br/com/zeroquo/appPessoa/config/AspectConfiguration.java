package br.com.zeroquo.appPessoa.config;

import br.com.zeroquo.appPessoa.aop.auditing.AuditingAspect;
import br.com.zeroquo.appPessoa.aop.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }

    @Bean
    public AuditingAspect auditingAspect(Environment env) {
        return new AuditingAspect(env);
    }
}
