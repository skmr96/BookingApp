package booking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.irctc.booking.model.Role;
import com.irctc.booking.model.Train;
import com.irctc.booking.model.User;
import com.irctc.booking.service.TrainService;
import com.irctc.booking.service.UserService;

@SpringBootTest
class IrctcBookingApplicationTests {
	
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private UserService userService;
	
	@Test
	void contextLoads() {
		assertThat(trainService).isNotNull();
		assertThat(userService).isNotNull();
	}
	
	@Test
	void addTrain()
	{
		Train train = new Train();
		train.setName("Pandiyan");
		Train addedTrain = trainService.addTrain(train);
		assertEquals("Pandiyan", addedTrain.getName());
	}
	
	@Test
	void addUser()
	{
		User user = new User();
		user.setName("John");
		user.setRole(Role.USER);
		User addedUser = userService.addUser(user);
		assertEquals("John", addedUser.getName());
	}
	
	@Test
	void addAdmin()
	{
		User user = new User();
		user.setName("Peter");
		user.setRole(Role.ADMIN);
		User addedUser = userService.addUser(user);
		assertEquals(user.getRole().getType(), addedUser.getRole().getType());
	}
	
	@Test
	void getAllTrains()
	{
		List<Train> trains = trainService.getAllTrains();
		assertThat(trains.size() > 1).isTrue();
	}

}
