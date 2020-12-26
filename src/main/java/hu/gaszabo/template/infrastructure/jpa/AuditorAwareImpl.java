package hu.gaszabo.template.infrastructure.jpa;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {

	private final Environment environment;

	@Autowired
	public AuditorAwareImpl(final Environment environment) {
		this.environment = requireNonNull(environment, "environment can't be null");
	}

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(environment.getProperty("audit.user", "user"));

	}
}
