package hu.gaszabo.template.infrastructure.jpa.sequence;

import java.time.Instant;
import java.util.UUID;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.UUIDSequence;
import org.eclipse.persistence.sessions.Session;

public class TimeOrderedUUIDSequence extends UUIDSequence implements SessionCustomizer {

	private static final long serialVersionUID = -9120245256808424877L;

	public TimeOrderedUUIDSequence() {
	}

	public TimeOrderedUUIDSequence(String name) {
		super(name);
	}

	@Override
	public Object getGeneratedValue(Accessor accessor, AbstractSession writeSession, String seqName) {
		return TimeOrderedUUID.get();
	}

	@Override
	public void customize(Session session) throws Exception {
		TimeOrderedUUIDSequence sequence = new TimeOrderedUUIDSequence("time-ordered-uuid");
		session.getLogin().addSequence(sequence);
	}

	private static class TimeOrderedUUID {

		public static synchronized UUID get() {
			long millis = Instant.now().toEpochMilli();
			long lsb = UUID.randomUUID().getLeastSignificantBits();

			long msb = (millis & 0x0ffffffffffff000L) << 4 //
					| millis & 0x0000000000000fffL //
					| 0x0000000000006000L;

			return new UUID(msb, lsb);
		}

	}

}
