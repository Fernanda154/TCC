import java.util.Calendar;

public class Teste {
	

		public static void main(String[] args) {
	    final Calendar c = Calendar.getInstance();
			
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH); 
			
			System.out.println(year);
			System.out.println(month + 1);
			System.out.println(day);

		}

	}

