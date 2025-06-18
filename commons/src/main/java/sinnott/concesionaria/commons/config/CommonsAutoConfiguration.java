package sinnott.concesionaria.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sinnott.concesionaria.commons.handlers.GlobalExceptionHandler;

/**
 * Auto-configuration class to automatically import the GlobalExceptionHandler.
 * This bean will be picked up by any service that includes this 'commons' module.
 */
@Configuration
@Import(GlobalExceptionHandler.class)
public class CommonsAutoConfiguration {
} 