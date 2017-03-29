package queueException;

public class MinPriorityQueueException {
	
	@SuppressWarnings("serial")
	public static class NoElementInQueueException extends Exception {
		public NoElementInQueueException(String message) {
			super(message);
		}
	}
	
	@SuppressWarnings("serial")
	public static class InvalidInputToQueueException extends Exception {
		public InvalidInputToQueueException(String message) {
			super(message);
		}
	}

}
