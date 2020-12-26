package hu.gaszabo.template.infrastructure.util;

public final class Preconditions {

	private Preconditions() {
		throw new UnsupportedOperationException("Preconditions utility class can't be instantiated");
	}

	public static void checkArgument(final boolean condition, final String errorMessage) {
		if (!condition) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkState(final boolean condition, final String errorMessage) {
		if (!condition) {
			throw new IllegalStateException(errorMessage);
		}
	}

}
