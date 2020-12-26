package hu.gaszabo.template.model;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import hu.gaszabo.template.infrastructure.jpa.persistententity.SequenceGeneratedPersistentEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "EVENT")
public class Event extends SequenceGeneratedPersistentEntity {

	private static final long serialVersionUID = -3484204371263141594L;

	private static final int LENGTH = 64;

	public static Event of(final String name, final String body) {
		return new Event(name, body);
	}

	@Column(name = "NAME", nullable = false, length = LENGTH)
	private String name;

	@Column(name = "BODY", length = 4000)
	private String body;

	protected Event() {
	}

	private Event(final String name, final String body) {
		this.name = requireNonNull(name, "name can't be null");
		this.body = body;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

}
