import org.junit.Assert;
import org.junit.Test;

public class SauceTests {

    // Test #1 - add 2 products to the shppping cart and complete buying process :
    @Test
    public void loginTest() {
        Sauce sauce = new Sauce();
        sauce.loginAs("standard_user", "secret_sauce");
        String expected = "https://www.saucedemo.com/inventory.html";
        String actual = sauce.location();
        Assert.assertEquals(expected, actual);
        sauce.click("#add-to-cart-sauce-labs-bike-light");
        sleep(2000);
        sauce.click("#add-to-cart-sauce-labs-fleece-jacket");
        sleep(2000);
        sauce.click("#shopping_cart_container > a");
        sleep(2000);
        sauce.click("#checkout");
        sleep(2000);
        sauce.FillInfo("bob", "mcguire", "546733");
        sleep(2000);
        sauce.finish();


    }


    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }

    // Test - Bug #1 - login to "standard_user" add products to the cart, then logout and login to
    // "problem_user" and the added products will appear in the shopping cart of "problem_user":
    @Test
    public void cartleak() {
        Sauce sauce = new Sauce();
        sauce.loginAs("standard_user", "secret_sauce");
        sauce.click("#add-to-cart-sauce-labs-bike-light");
        sauce.logOut();
        sauce.loginAs("problem_user", "secret_sauce");
        String cartBadge = "#shopping_cart_container > a > span";
        sauce.assertNotExist(cartBadge);

    }


    //Test - Bug #2 - the outer price doesn't match the inner price of the relevant product:
    @Test
    public void itemPrice() {
        Sauce sauce = new Sauce();
        sauce.loginAs("problem_user", "secret_sauce");
        String OuterPrice = sauce.getText("#inventory_container > div > div:nth-child(1) > div.inventory_item_description > div.pricebar > div");
        sauce.click("#item_4_title_link > div");
        String InnerPrice = sauce.getText("#inventory_item_container > div > div > div.inventory_details_desc_container > div.inventory_details_price");
        Assert.assertEquals(OuterPrice, InnerPrice);


    }

    //Test - Bug #3 -when adding a product by clicking on "add to cart" button then clicking on "remove" button
    //but the "remove" button doesn't respond:
    @Test
    public void RemoveFromCart() {
        Sauce sauce = new Sauce();
        sauce.loginAs("problem_user", "secret_sauce");
        sauce.click("#add-to-cart-sauce-labs-backpack");
        sauce.click("#remove-sauce-labs-backpack");
        sauce.click("#add-to-cart-sauce-labs-backpack");

    }
}
