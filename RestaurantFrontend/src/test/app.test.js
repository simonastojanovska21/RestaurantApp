import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';
import Header from "../components/Header/header";
import Footer from "../components/Footer/footer";
import Home from "../components/Home/home";
import Login from '../components/Login/login';
import Register from "../components/Register/register";
import MealCategoryList from "../components/MealCategory/mealCategoryList";
import MealCategoryAdd from "../components/MealCategory/mealCategoryAdd";
import MealCategoryEdit from "../components/MealCategory/mealCategoryEdit";
import IngredientEdit from "../components/Ingredients/IngredientEdit";
import IngredientAdd from "../components/Ingredients/IngredientAdd";
import IngredientList from "../components/Ingredients/IngredientList";
import Menu from "../components/Menu/MenuList";
import MealAdd from "../components/Menu/MealAdd";
import MealDetails from "../components/Menu/MealDetails";
import MealEdit from "../components/Menu/MealEdit";
import ShoppingCart from "../components/ShoppingCart/ShoppingCart";
import CheckOutOrder from "../components/ShoppingCart/CheckOutOrder";
import OrderList from "../components/Orders/OrderList";
import DeliveriesList from "../components/Deliveries/DeliveriesList";
import Profile from "../components/User/Profile";

describe('Mount components from <App>',()=>{

    afterEach(() => {
        localStorage.clear();
    });

    it('<App> contains <Header> component',()=>{
        const wrapper=mount(<App/>);
        expect(wrapper.find(Header)).to.have.length(1);
    })

    it('<App> contains <Home> component',()=>{
        const wrapper=mount(<App/>);
        expect(wrapper.find(Home)).to.have.length(1);
    })

    it('<App> contains <Login> component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'login','/login');
        const wrapper=mount(<App/>);
        expect(wrapper.find(Login)).to.have.length(1);

        const instance = wrapper.instance();
        instance.loginUser('admin@admin.com','P@ssword');
        instance.logoutUser();
    })

    it('<App> contains <Register > component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'login','/register');
        const wrapper=mount(<App/>);
        expect(wrapper.find(Register)).to.have.length(1);
        const instance = wrapper.instance();
        instance.registerUser('test@test.com','P@assword','P@assword','Test name','Test surname','070123456','Test address');
    })

    it('<App> contains <Profile > component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'login','/profile');
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');
        const wrapper=mount(<App/>);
        expect(wrapper.find(Profile )).to.have.length(1);
        const instance = wrapper.instance();
        instance.leaveReview(5,'Super','admin@admin.com')
    })


    it('mount <IngredientEdit> component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'deliveries','/ingredients/edit/2');
        localStorage.setItem("userRole","ROLE_ADMIN");
        const wrapper=mount(<App/>);
        expect(wrapper.find(IngredientEdit)).to.have.length(1);

        const instance = wrapper.instance();
        instance.getIngredient(30);
        instance.editIngredient(30,'Edited fish',10)
    })

    it('mount <IngredientAdd> component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'deliveries','/ingredients/add');
        localStorage.setItem("userRole","ROLE_ADMIN");
        const wrapper=mount(<App/>);
        expect(wrapper.find(IngredientAdd)).to.have.length(1);

        const instance = wrapper.instance();
        instance.addIngredient('Ice cream',52);
    })

    it('mount <IngredientList> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'deliveries','/ingredients');

        const wrapper=mount(<App/>);
        expect(wrapper.find(IngredientList)).to.have.length(1);

        const instance = wrapper.instance();
        instance.deleteIngredient(11);
    })

    it('mount <MealCategoryEdit> component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal category','/mealCategories/edit/:id');
        localStorage.setItem("userRole","ROLE_ADMIN");
        const wrapper=mount(<App/>);
        expect(wrapper.find(MealCategoryEdit)).to.have.length(1);

        const instance = wrapper.instance();
        instance.getMealCategory(10);
        instance.editMealCategory(10,'EditedName','https://test')
    })

    it('mount <MealCategoryAdd> component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal category','/mealCategories/add');
        localStorage.setItem("userRole","ROLE_ADMIN");
        const wrapper=mount(<App/>);
        expect(wrapper.find(MealCategoryAdd)).to.have.length(1);

        const instance = wrapper.instance();
        instance.addMealCategory('Test name','Test image');
    })

    it('mount <MealCategoryList> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal category','/mealCategories');

        const wrapper=mount(<App/>);
        expect(wrapper.find(MealCategoryList)).to.have.length(1);

        const instance = wrapper.instance();
        instance.deleteMealCategory(11);

    })

    it('mount <Menu> component with filtered meal categories',()=>{
        localStorage.setItem("selectedCategoryId",2)
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal category','/menu/mealCategory/2');

        const wrapper=mount(<App/>);
        expect(wrapper.find(Menu)).to.have.length(1);

        const instance = wrapper.instance();
        instance.loadMealsFromMealCategory(2);
    })

    it('mount <MealAdd> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal add','/meal/add');
        const wrapper=mount(<App/>);
        expect(wrapper.find(MealAdd)).to.have.length(1);

        const instance = wrapper.instance();
        instance.addNewMealToMenu('Test name', 'Test description',21,5,'https://testImage',[1,2,3]);
    })

    it('mount <MealEdit> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal add','/meal/edit/2');
        const wrapper=mount(<App/>);
        expect(wrapper.find(MealEdit)).to.have.length(1);

        const instance = wrapper.instance();
        instance.editMealFromMenu(2,'EditedName','EditedDescription',14,4,'https://image',[1,2,3,4],)
    })

    it('mount <MealDetails> component',()=>{
        localStorage.setItem("selectedMealId",2)
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal add','/meal/details/2');
        const wrapper=mount(<App/>);
        expect(wrapper.find(MealDetails)).to.have.length(1);
        const instance = wrapper.instance();
        instance.getMealDetails(2);
    })

    it('mount <Menu> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal add','/menu');
        const wrapper=mount(<App/>);
        expect(wrapper.find(Menu)).to.have.length(1);

        const instance = wrapper.instance();
        instance.deleteMealFromMenu(1);
    })

    it('mount <ShoppingCart> component',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal add','/shoppingCart');
        const wrapper=mount(<App/>);
        expect(wrapper.find(ShoppingCart)).to.have.length(1);

        const instance = wrapper.instance();

        instance.getOrderItemsForUser('admin@admin.com');

        setTimeout(()=>{
            expect(wrapper.state().orderItems.length).to.equal(2)
        },1000)
    })

    it('mount <CheckOutOrder> component',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');
        localStorage.setItem("selectedOrderId",1);
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'meal add','/checkOut');
        const wrapper=mount(<App/>);
        expect(wrapper.find(CheckOutOrder)).to.have.length(1);
    })

    it('mount <OrderList> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'deliveries','/orders/processing');
        const wrapper=mount(<App/>);
        expect(wrapper.find(OrderList)).to.have.length(1);

    })

    it('mount <DeliveriesList> component',()=>{
        localStorage.setItem("userRole","ROLE_ADMIN");
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'deliveries','/deliveries');
        const wrapper=mount(<App/>);
        expect(wrapper.find(DeliveriesList)).to.have.length(1);

    })

    it('<App> contains <Footer> component',()=>{
        const wrapper=mount(<App/>);
        expect(wrapper.find(Footer)).to.have.length(1);
    })
})