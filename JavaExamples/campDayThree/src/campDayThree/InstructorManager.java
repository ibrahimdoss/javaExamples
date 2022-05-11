package campDayThree;

public class InstructorManager extends UserManager {
	
	@Override
	public void add(User user) {
		System.out.println(user.getName() + " adl� e�itmen sisteme eklendi.");
	}

	@Override
	public void delete(User user) {
		System.out.println(user.getName() + " adl� e�itmen sistemden silindi.");
	}

	@Override
	public void update(User user) {
		System.out.println(user.getName() + " adl� e�itmenin bilgileri g�ncellendi.");
	}
	
	public void getAll(Instructor[] Instructors) {
		for (Instructor instructors : Instructors) {
			System.out.println(instructors.getName());
			System.out.println("---------------");
		}
	}

}
