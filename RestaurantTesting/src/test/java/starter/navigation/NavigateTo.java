package starter.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import starter.navigation.pages.*;

public class NavigateTo {

    public static Performable theRestaurantHomePage(){
        return Task.where("{0} opens the restaurant home page",
                Open.browserOn().the(HomePage.class));
    }
    public static Performable theLoginPage(){
        return Task.where("{0} opens the login page",
                Open.browserOn().the(LoginPage.class));
    }
    public static Performable toRegisterPage(){
        return Task.where("{0} opens register page",
                Open.browserOn().the(RegisterPage.class));
    }
    public static Performable toProfilePage(){
        return Task.where("{0} opens profile page",
                Open.browserOn().the(ProfilePage.class));
    }

    public static Performable toMealCategoriesAddPage(){
        return Task.where("{0} opens meal categories page",
                Open.browserOn().the(MealCategoriesAddPage.class));
    }
    public static Performable toMealCategoriesEditPage(Long id){
        return Task.where("{0} opens meal categories edit page",
                Open.url("http://localhost:3000/mealCategories/edit/"+id));
    }
    public static Performable toMealCategoriesPage(){
        return Task.where("{0} opens meal categories page",
                Open.browserOn().the(MealCategoriesPage.class));
    }

    public static Performable toIngredientsAddPage() {
        return Task.where("{0} opens ingredients add page",
                Open.browserOn().the(IngredientsAddPage.class));
    }
    public static Performable toIngredientEditPage(Long id){
        return Task.where("{0} opens ingredient edit page",
                Open.url("http://localhost:3000/ingredients/edit/"+id));
    }
    public static Performable toIngredientsPage(){
        return Task.where("{0} opens ingredients page",
                Open.browserOn().the(IngredientsPage.class));
    }

    public static Performable toMealAddPage() {
        return Task.where("{0} opens meal add page",
                Open.browserOn().the(MealAddPage.class));
    }
    public static Performable toMealEditPage(Long id) {
        return Task.where("{0} opens meal edit page",
                Open.url("http://localhost:3000/meal/edit/2"+id));
    }
    public static Performable toMenuPage() {
        return Task.where("{0} opens menu page",
                Open.browserOn().the(MenuPage.class));
    }

    public static Performable theShoppingCartPage(){
        return Task.where("{0} opens shopping cart page",
                Open.browserOn().the(ShoppingCartPage.class));
    }
    public static Performable toCheckOutPage(){
        return Task.where("{0} opens check out page",
                Open.browserOn().the(CheckOutPage.class));
    }
    public static Performable toProcessingOrdersPage(){
        return Task.where("{0} opens processing orders page",
                Open.browserOn().the(ProcessingOrdersPage.class));
    }
    public static Performable toDeliveriesPage(){
        return Task.where("{0} opend delivery page",
                Open.browserOn().the(DeliveriesPage.class));
    }



}
