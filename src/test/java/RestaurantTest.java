import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    //spoof variable acts as the menu selected by the user
    List<Item> item = new ArrayList<Item>();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    public void createRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        createRestaurant();
        restaurant.setClosingTime(LocalTime.now().plusMinutes(10));
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        createRestaurant();
        restaurant.setClosingTime(LocalTime.now().minusMinutes(10));
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        createRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        createRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        createRestaurant();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void order_value_should_get_cumulative_total_when_collection_of_items_selected(){
        createRestaurant();
        item = restaurant.getMenu();
        assertEquals(430,restaurant.getOrderValue(item));
    }

    @Test
    public void order_value_should_reduce_cumulative_total_when_an_item_removed(){
        createRestaurant();
        item = restaurant.getMenu();
        int total = restaurant.getOrderValue(item);
        int afterTotal = item.get(1).getPrice();
        item.remove(1);
        assertEquals(total-afterTotal,restaurant.getOrderValue(item));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}