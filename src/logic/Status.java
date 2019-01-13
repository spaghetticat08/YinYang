package logic;

public enum Status {
	Voltooid{
		public String toString() {
			return "Voltooid";
		}
	},
	Geannuleerd{
		public String toString() {
			return "Geannuleerd";
		}
	},
	AfwachtingBetaling{
		//it would be much nicer to add a space here but it seems java throws an illegalargumentException. How to fix this?
		public String toString() {
			return "AfwachtingBetaling";
		}
	}
	
	
}
