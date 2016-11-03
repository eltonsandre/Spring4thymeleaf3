package br.com.modulo.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.modulo.core.service.AbstractService;


@Configuration
@ComponentScan(basePackageClasses = AbstractService.class)
public class ServiceConfig {


}
