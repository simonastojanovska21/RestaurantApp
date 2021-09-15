import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';

import MenuList from "../components/Menu/MenuList";
import MealAdd from "../components/Menu/MealAdd";
import MealDetails from "../components/Menu/MealDetails";
import MealEdit from "../components/Menu/MealEdit";
import MenuItem from "../components/Menu/MenuItem";
import AddMenuItemInOrder from "../components/Menu/AddMenuItemInOrder";

import {BrowserRouter as Router} from 'react-router-dom';
import MealCategory from "../components/Home/mealCategory";



const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

const spyDelete=chai.spy.on(App.prototype,'deleteMealFromMenu');
const spyMealClick=chai.spy.on(App.prototype,'getMealDetails');
const spyFilter=chai.spy.on(App.prototype,'loadMealsFromMealCategory');
const spyAddToOrder=chai.spy.on(App.prototype,'addOrderItemInOrder');
const spyAddMeal=chai.spy.on(App.prototype,'addNewMealToMenu');
const spyEditMeal=chai.spy.on(App.prototype,'editMealFromMenu');
const menu=[
    {"id":1,"name":"Meal for delete","description":"Meal for delete description","price":120.0,
        "mealCategory":{"id":2,"name":"Pizza","imageUrl":"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg"},
        "imageUrl":"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg",
        "ingredientsForMeal":[{"id":1,"quantity":80,"name":"Cheese"},{"id":2,"quantity":50,"name":"Pepperoni"},{"id":3,"quantity":45,"name":"Mushrooms"},{"id":4,"quantity":45,"name":"Pepper"}]},
    {"id":2,"name":"Meal for edit","description":"Meal for edit description","price":122.0,
        "mealCategory":{"id":6,"name":"Salad","imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/m/Malaysian-style-rojak-pineapple-salad.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"},
        "imageUrl":"https://food-guide.canada.ca/sites/default/files/styles/square_400_x_400/public/2020-12/CFGPlate-crop400x400.jpg",
        "ingredientsForMeal":[{"id":5,"quantity":45,"name":"Onion"},{"id":6,"quantity":85,"name":"Mozzarella"},{"id":7,"quantity":45,"name":"Tuna"},{"id":8,"quantity":233,"name":"Lettuce"}]},
    {"id":3,"name":"Meal1","description":"Meal1 description","price":50.0,
        "mealCategory":{"id":7,"name":"Pasta","imageUrl":"https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg"},
        "imageUrl":"https://www.bosscaffe.com/sites/default/files/styles/product_thumb/public/2019-04/PASTA_BOLOGNESE.jpg?itok=xlPQ0A54",
        "ingredientsForMeal":[{"id":1,"quantity":80,"name":"Cheese"},{"id":15,"quantity":85,"name":"Bacon"},{"id":16,"quantity":25,"name":"Ketchup"},{"id":24,"quantity":85,"name":"Spaghetti"}]},
    {"id":4,"name":"Meal2","description":"Meal2 description","price":10.0,
        "mealCategory":{"id":5,"name":"Breakfast","imageUrl":"https://assets.epicurious.com/photos/5e95fb10febe90000898aff8/1:1/w_400%2Cc_limit/PlantainBreakfast_HERO_041020_6266.jpg"},
        "imageUrl":"https://hips.hearstapps.com/del.h-cdn.co/assets/cm/15/10/54f63ec6bc6d3_-_gluten-free-banana-coconut-pancakes-recipe-fw0814-de.jpg",
        "ingredientsForMeal":[{"id":18,"quantity":78,"name":"Waffle"},{"id":19,"quantity":45,"name":"Apple"},{"id":20,"quantity":85,"name":"Honey"}]},
    {"id":5,"name":"Meal3","description":"Meal3 description","price":32.0,
        "mealCategory":{"id":1,"name":"Seafood","imageUrl":"https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png"},
        "imageUrl":"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15548.jpg?ext=.jpg",
        "ingredientsForMeal":[{"id":4,"quantity":45,"name":"Pepper"},{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":29,"quantity":23,"name":"Salmon"},{"id":31,"quantity":85,"name":"Shrimps"}]},
    {"id":6,"name":"Meal4","description":"Meal4 description","price":12.0,
        "mealCategory":{"id":2,"name":"Pizza","imageUrl":"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg"},
        "imageUrl":"https://www.woolwichdairy.com/-/media/ecosystem/divisions/canada-dairy/sites/woolwich-dairy/woolwich-dairy-images/recipes/pinterest/classic-veggie-pizza-400x400.ashx?revision=edb57bc9-56e4-4a68-9624-ee641b9d13ed",
        "ingredientsForMeal":[{"id":1,"quantity":80,"name":"Cheese"},{"id":2,"quantity":50,"name":"Pepperoni"},{"id":3,"quantity":45,"name":"Mushrooms"},{"id":6,"quantity":85,"name":"Mozzarella"},{"id":16,"quantity":25,"name":"Ketchup"},{"id":32,"quantity":98,"name":"Tomato"}]},
    {"id":7,"name":"Meal5","description":"Meal5 description","price":50.0,
        "mealCategory":{"id":9,"name":"Main meal","imageUrl":"https://assets.epicurious.com/photos/5dc7263dd482f10008d4fadb/1:1/w_400%2Cc_limit/HoneyMustardChicken_RECIPE_110519_5882.jpg"},
        "imageUrl":"https://www.bbcgoodfoodme.com/assets/recipes/25261/original/tenderloin.png",
        "ingredientsForMeal":[{"id":8,"quantity":233,"name":"Lettuce"},{"id":9,"quantity":87,"name":"Chicken"},{"id":15,"quantity":85,"name":"Bacon"},{"id":22,"quantity":45,"name":"Rice"},{"id":27,"quantity":85,"name":"Pork"}]},
    {"id":8,"name":"Meal6","description":"Meal6 description","price":50.0,
        "mealCategory":{"id":3,"name":"Dessert","imageUrl":"https://pbs.twimg.com/profile_images/645988651109904384/3ljrC_8j_400x400.jpg"},
        "imageUrl":"https://www.readyseteat.com/sites/g/files/qyyrlu501/files/uploadedImages/img_2059_952.jpg",
        "ingredientsForMeal":[{"id":17,"quantity":45,"name":"Pancake"},{"id":20,"quantity":85,"name":"Honey"},{"id":21,"quantity":32,"name":"Cinnamon"},{"id":33,"quantity":222,"name":"Milk"}]},
    {"id":9,"name":"Meal7","description":"Meal7 description","price":20.0,
        "mealCategory":{"id":6,"name":"Salad","imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/m/Malaysian-style-rojak-pineapple-salad.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"},
        "imageUrl":"https://img.allw.mn/food/thumbs/y8/yv/v4wvynv2_400x400.jpg",
        "ingredientsForMeal":[{"id":4,"quantity":45,"name":"Pepper"},{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":9,"quantity":87,"name":"Chicken"},{"id":10,"quantity":45,"name":"Croutons"},{"id":12,"quantity":122,"name":"Corn"},{"id":13,"quantity":45,"name":"Cucumber"},{"id":32,"quantity":98,"name":"Tomato"}]},
    {"id":10,"name":"Meal8","description":"Meal8 description","price":27.0,
        "mealCategory":{"id":8,"name":"Burger","imageUrl":"https://tastesbetterfromscratch.com/wp-content/uploads/2016/03/Black-Bean-Burger-8-400x400.jpg"},
        "imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/b/E2E_WaitroseWeekendSunshineSpecial_36117_BestBurgers.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png",
        "ingredientsForMeal":[{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":15,"quantity":85,"name":"Bacon"},{"id":25,"quantity":85,"name":"Potato"},{"id":27,"quantity":85,"name":"Pork"}]},
    {"id":11,"name":"Meal9","description":"Meal9 description","price":32.0,
        "mealCategory":{"id":7,"name":"Pasta","imageUrl":"https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg"},
        "imageUrl":"https://taste.co.za/wp-content/uploads/2015/03/fiorelli-pasta-with-smoked-salmon-and-cavi-art-3650-400x400.jpg",
        "ingredientsForMeal":[{"id":3,"quantity":45,"name":"Mushrooms"},{"id":16,"quantity":25,"name":"Ketchup"},{"id":23,"quantity":78,"name":"Macaroni"},{"id":31,"quantity":85,"name":"Shrimps"}]},
    {"id":12,"name":"Meal10","description":"Meal10 description","price":10.0,
        "mealCategory":{"id":4,"name":"Sandwich", "imageUrl":"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef9289a07b0_-_vietnamese-turkey-sandwich-recipe-wdy1113-de.jpg"},
        "imageUrl":"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15484.jpg?ext=.jpg",
        "ingredientsForMeal":[{"id":1,"quantity":80,"name":"Cheese"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":13,"quantity":45,"name":"Cucumber"},{"id":28,"quantity":65,"name":"Turkey"}]}]
const mealCategories=[
    {"id":1,"name":"Seafood","imageUrl":"https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png","numberOfMeals":1},
    {"id":2,"name":"Pizza","imageUrl":"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg","numberOfMeals":2},
    {"id":3,"name":"Dessert","imageUrl":"https://pbs.twimg.com/profile_images/645988651109904384/3ljrC_8j_400x400.jpg","numberOfMeals":1},
    {"id":4,"name":"Sandwich","imageUrl":"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef9289a07b0_-_vietnamese-turkey-sandwich-recipe-wdy1113-de.jpg","numberOfMeals":1},
    {"id":5,"name":"Breakfast","imageUrl":"https://assets.epicurious.com/photos/5e95fb10febe90000898aff8/1:1/w_400%2Cc_limit/PlantainBreakfast_HERO_041020_6266.jpg","numberOfMeals":1},
    {"id":6,"name":"Salad","imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/m/Malaysian-style-rojak-pineapple-salad.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png","numberOfMeals":2},
    {"id":7,"name":"Pasta","imageUrl":"https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg","numberOfMeals":2},
    {"id":8,"name":"Burger","imageUrl":"https://tastesbetterfromscratch.com/wp-content/uploads/2016/03/Black-Bean-Burger-8-400x400.jpg","numberOfMeals":1},
    {"id":9,"name":"Main meal","imageUrl":"https://assets.epicurious.com/photos/5dc7263dd482f10008d4fadb/1:1/w_400%2Cc_limit/HoneyMustardChicken_RECIPE_110519_5882.jpg","numberOfMeals":1},
    {"id":10,"name":"Meal category for edit","imageUrl":"https://i.pinimg.com/originals/c9/c2/b1/c9c2b12a2b325f0080a1f328a0963341.jpg","numberOfMeals":0},
    {"id":11,"name":"Meal category for delete","imageUrl":"https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_400,q_40,w_400/v1/clients/richmondbc/onceuponatime_90_DSCF2732_d974c753-6b91-4db8-abc4-cf8a1b9ef32d.jpg","numberOfMeals":0}]
const meal={"id":10,"name":"Meal8","description":"Meal8 description","price":27.0,
    "mealCategory":{"id":8,"name":"Burger","imageUrl":"https://tastesbetterfromscratch.com/wp-content/uploads/2016/03/Black-Bean-Burger-8-400x400.jpg"},
    "imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/b/E2E_WaitroseWeekendSunshineSpecial_36117_BestBurgers.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png",
    "ingredientsForMeal":[{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":15,"quantity":85,"name":"Bacon"},{"id":25,"quantity":85,"name":"Potato"},{"id":27,"quantity":85,"name":"Pork"}]};
const ingredients=[{"id":1,"quantity":80,"name":"Cheese"},{"id":2,"quantity":50,"name":"Pepperoni"},
    {"id":3,"quantity":45,"name":"Mushrooms"},{"id":4,"quantity":45,"name":"Pepper"},
    {"id":5,"quantity":45,"name":"Onion"},{"id":6,"quantity":85,"name":"Mozzarella"},
    {"id":7,"quantity":45,"name":"Tuna"},{"id":8,"quantity":233,"name":"Lettuce"},
    {"id":9,"quantity":87,"name":"Chicken"},{"id":10,"quantity":45,"name":"Croutons"},
    {"id":11,"quantity":74,"name":"Carrot"},{"id":12,"quantity":122,"name":"Corn"},
    {"id":13,"quantity":45,"name":"Cucumber"},{"id":14,"quantity":123,"name":"Egg"},
    {"id":15,"quantity":85,"name":"Bacon"},{"id":16,"quantity":25,"name":"Ketchup"},
    {"id":17,"quantity":45,"name":"Pancake"},{"id":18,"quantity":78,"name":"Waffle"},
    {"id":19,"quantity":45,"name":"Apple"},{"id":20,"quantity":85,"name":"Honey"},
    {"id":21,"quantity":32,"name":"Cinnamon"},{"id":22,"quantity":45,"name":"Rice"},
    {"id":23,"quantity":78,"name":"Macaroni"},{"id":24,"quantity":85,"name":"Spaghetti"},
    {"id":25,"quantity":85,"name":"Potato"},{"id":26,"quantity":45,"name":"Lime"},
    {"id":27,"quantity":85,"name":"Pork"},{"id":28,"quantity":65,"name":"Turkey"},
    {"id":29,"quantity":23,"name":"Salmon"},{"id":30,"quantity":23,"name":"Fish"},
    {"id":31,"quantity":85,"name":"Shrimps"},{"id":32,"quantity":98,"name":"Tomato"},
    {"id":33,"quantity":222,"name":"Milk"}]
const topMeals=[
    {"id":4,"name":"Meal2","description":"Meal2 description","price":10.0,
        "mealCategory":{"id":5,"name":"Breakfast","imageUrl":"https://assets.epicurious.com/photos/5e95fb10febe90000898aff8/1:1/w_400%2Cc_limit/PlantainBreakfast_HERO_041020_6266.jpg"},
        "imageUrl":"https://hips.hearstapps.com/del.h-cdn.co/assets/cm/15/10/54f63ec6bc6d3_-_gluten-free-banana-coconut-pancakes-recipe-fw0814-de.jpg",
        "ingredientsForMeal":[{"id":18,"quantity":78,"name":"Waffle"},{"id":19,"quantity":45,"name":"Apple"},{"id":20,"quantity":85,"name":"Honey"}]},
    {"id":9,"name":"Meal7","description":"Meal7 description","price":20.0,
        "mealCategory":{"id":6,"name":"Salad","imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/m/Malaysian-style-rojak-pineapple-salad.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"},
        "imageUrl":"https://img.allw.mn/food/thumbs/y8/yv/v4wvynv2_400x400.jpg",
        "ingredientsForMeal":[{"id":4,"quantity":45,"name":"Pepper"},{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":9,"quantity":87,"name":"Chicken"},{"id":10,"quantity":45,"name":"Croutons"},{"id":12,"quantity":122,"name":"Corn"},{"id":13,"quantity":45,"name":"Cucumber"},{"id":32,"quantity":98,"name":"Tomato"}]},
    {"id":10,"name":"Meal8","description":"Meal8 description","price":27.0,
        "mealCategory":{"id":8,"name":"Burger","imageUrl":"https://tastesbetterfromscratch.com/wp-content/uploads/2016/03/Black-Bean-Burger-8-400x400.jpg"},
        "imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/b/E2E_WaitroseWeekendSunshineSpecial_36117_BestBurgers.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png",
        "ingredientsForMeal":[{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":15,"quantity":85,"name":"Bacon"},{"id":25,"quantity":85,"name":"Potato"},{"id":27,"quantity":85,"name":"Pork"}]},
    {"id":11,"name":"Meal9","description":"Meal9 description","price":32.0,
        "mealCategory":{"id":7,"name":"Pasta","imageUrl":"https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg"},
        "imageUrl":"https://taste.co.za/wp-content/uploads/2015/03/fiorelli-pasta-with-smoked-salmon-and-cavi-art-3650-400x400.jpg",
        "ingredientsForMeal":[{"id":3,"quantity":45,"name":"Mushrooms"},{"id":16,"quantity":25,"name":"Ketchup"},{"id":23,"quantity":78,"name":"Macaroni"},{"id":31,"quantity":85,"name":"Shrimps"}]}]

describe('<MenuList> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <MenuList> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MenuList meals={menu}
                  mealCategories={mealCategories}
                  onDeleteMeal={spyDelete}
                  onMealClick={spyMealClick}
                  onFilterByCategory={spyFilter}
                  onAddItemToOrder={spyAddToOrder} />
        </Router>)

        expect(wrapper.find(MenuItem)).to.have.length(12);
        wrapper.find('.mealCategory-1').at(1).simulate('click');
    })

    it('<MealCateogroy> component',()=>{
        const term={
            id:1,
            name:'Seafood',
            imageUrl:'https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png'
        }

        const wrapper=mount(<MealCategory category={term} onFilterByCategory={spyFilter} />);
        expect(wrapper.find('.mealCategory-1')).to.have.length(1);
        wrapper.find('.mealCategory-1').simulate('click');
    })

})

describe('<MenuItem> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('<MenuItem> mount component',()=>{
        localStorage.setItem("userRole","ADMIN");
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');

        const wrapper=mount(<Router>
            <MenuItem meal={meal}
                      onDelete={spyDelete}
                      onMealClick={spyMealClick}
                      onAddItemToOrder={spyAddToOrder} />
        </Router>)


        wrapper.find('.meal-10').at(1).simulate('click');
        wrapper.find('.mealEdit-10').at(1).simulate('click');
        wrapper.find('.mealDelete-10').at(1).simulate('click');

    })
})

describe('<MenuDetails> component', ()=> {
    afterEach(() => {
         localStorage.clear();
     });

    it('mount <MenuDetails> component',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');
        const wrapper=mount(<Router>
            <MealDetails selectedMeal={meal}
                         ingredients={ingredients}
                         topOrderedMeals={topMeals}
                         onDeleteMeal={spyDelete}
                         onMealClick={spyMealClick}
                         onAddItemToOrder={spyAddToOrder}  />
        </Router>)

        expect(wrapper.find(MenuItem)).to.have.length(4);
    })

});

describe('<AddMenuItemInOrder> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <AddMenuItemInOrder> component',()=>{

        const wrapper=mount(<AddMenuItemInOrder mealId={10}
                                                onAddItemToOrder={spyAddToOrder}/>)

        expect(wrapper.find('.plusButton-10')).to.have.length(1)
        wrapper.find('.plusButton-10').simulate('click')

        expect(wrapper.find('.minusButton-10')).to.have.length(1)
        wrapper.find('.minusButton-10').simulate('click');

        expect(wrapper.find('.addButton-10')).to.have.length(1)

        expect(wrapper.find('#addItemToOrder')).to.have.length(1);
        wrapper.find('#addItemToOrder').simulate('submit');
    })
})

describe('<MealAdd> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <MealAdd> component without credentials',()=>{

        const wrapper=mount(<Router>
            <MealAdd onAddMeal={spyAddMeal}
                     ingredients={ingredients}
                     mealCategories={mealCategories}/> }
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <MealAdd> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MealAdd onAddMeal={spyAddMeal}
                     ingredients={ingredients}
                     mealCategories={mealCategories}/> }
        </Router>)

        expect(wrapper.find('#mealName')).to.have.length(1)
        expect(wrapper.find('#mealDescription')).to.have.length(1)
        expect(wrapper.find('#mealPrice')).to.have.length(1)
        expect(wrapper.find('#mealMealCategory')).to.have.length(1)
        expect(wrapper.find('#mealImageUrl')).to.have.length(1)
        expect(wrapper.find('#ingredientsForMeal')).to.have.length(1)

        const name=wrapper.find('#mealName');
        const description=wrapper.find('#mealDescription');
        const price=wrapper.find('#mealPrice');
        const category=wrapper.find('#mealMealCategory');
        const image=wrapper.find('#mealImageUrl');
        const ingredientsForMeal=wrapper.find('#ingredientsForMeal');

        const eventName={target: {name: "name", value: "name"}};
        name.simulate('change',eventName);

        const eventDescription={target: {name: "description", value: "description"}};
        description.simulate('change',eventDescription);

        const eventPrice={target: {name: "price", value: "20"}};
        price.simulate('change',eventPrice);

        const eventCategory={target: {name: "mealCategory", value: "3"}};
        category.simulate('change',eventCategory);

        const eventImage={target: {name: "imageUrl", value: "image"}};
        image.simulate('change',eventImage);

        const eventIngredients = {target: {name: "ingredientsForMeal", value: "2"}};
        ingredientsForMeal.simulate('change',eventIngredients)

        expect(wrapper.find('#addMeal')).to.have.length(1)
        wrapper.find('#addMeal').simulate('submit')
    })
})

describe('<MealEdit> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <MealEdit> component with no credentials',()=>{
        localStorage.clear();
        const wrapper=mount(<Router>
            <MealEdit onEditMeal={spyEditMeal}
                      ingredients={ingredients}
                      mealCategories={mealCategories}
                      selectedMeal={meal}/>
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <MealEdit> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MealEdit onEditMeal={spyEditMeal}
                      ingredients={ingredients}
                      mealCategories={mealCategories}
                      selectedMeal={meal}/>
        </Router>)

        expect(wrapper.find('#editMealName')).to.have.length(1)
        expect(wrapper.find('#editMealDescription')).to.have.length(1)
        expect(wrapper.find('#editMealPrice')).to.have.length(1)
        expect(wrapper.find('#editMealMealCategory')).to.have.length(1)
        expect(wrapper.find('#editMealImageUrl')).to.have.length(1)
        expect(wrapper.find('#editIngredientsForMeal')).to.have.length(1)

        const name=wrapper.find('#editMealName');
        const description=wrapper.find('#editMealDescription');
        const price=wrapper.find('#editMealPrice');
        const category=wrapper.find('#editMealMealCategory');
        const image=wrapper.find('#editMealImageUrl');
        const ingredientsForMeal=wrapper.find('#editIngredientsForMeal');

        const eventName={target: {name: "name", value: "name"}};
        name.simulate('change',eventName);

        const eventDescription={target: {name: "description", value: "description"}};
        description.simulate('change',eventDescription);

        const eventPrice={target: {name: "price", value: "20"}};
        price.simulate('change',eventPrice);

        const eventCategory={target: {name: "mealCategory", value: "3"}};
        category.simulate('change',eventCategory);

        const eventImage={target: {name: "imageUrl", value: "image"}};
        image.simulate('change',eventImage);

        const eventIngredients = {target: {name: "ingredientsForMeal", value: "2"}};
        ingredientsForMeal.simulate('change',eventIngredients)

        expect(wrapper.find('#editMeal')).to.have.length(1)
        wrapper.find('#editMeal').simulate('submit')
    })

    it('mount <MealEdit> component with no edit data',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MealEdit onEditMeal={spyEditMeal}
                      ingredients={ingredients}
                      mealCategories={mealCategories}
                      selectedMeal={meal}/>
        </Router>)

        expect(wrapper.find('#editMealName')).to.have.length(1)
        expect(wrapper.find('#editMealDescription')).to.have.length(1)
        expect(wrapper.find('#editMealPrice')).to.have.length(1)
        expect(wrapper.find('#editMealMealCategory')).to.have.length(1)
        expect(wrapper.find('#editMealImageUrl')).to.have.length(1)
        expect(wrapper.find('#editIngredientsForMeal')).to.have.length(1)

        expect(wrapper.find('#editMeal')).to.have.length(1)
        wrapper.find('#editMeal').simulate('submit')
    })
})

// describe('<MealCategoryAdd> component',()=>{
//     afterEach(() => {
//         localStorage.clear();
//     });
//     const spyAdd=chai.spy.on(App.prototype,'addMealCategory');
//
//     it('mount <MealCategoryAdd> with no credentials',()=>{
//
//         const wrapper=mount(<Router>
//             <MealCategoryAdd onAddMealCategory={spyAdd} />
//         </Router>)
//
//         expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
//     })
//
//     it('mount <MealCategoryAdd> component',()=>{
//         localStorage.setItem("userRole","ADMIN");
//         const wrapper=mount(<Router>
//             <MealCategoryAdd onAddMealCategory={spyAdd} />
//         </Router>)
//
//         expect(wrapper.find('#mealCategoryName')).to.have.length(1)
//         expect(wrapper.find('#mealCategoryImageUrl')).to.have.length(1)
//
//         const name=wrapper.find('#mealCategoryName')
//         const image=wrapper.find('#mealCategoryImageUrl')
//
//         const event1 = {target: {name: "name", value: "Test meal category"}};
//         name.simulate('change',event1);
//
//         const event2 = {target: {name: "imageUrl", value: "testMealCategoryImage"}};
//         image.simulate('change',event2)
//
//         expect(wrapper.find('#mealCategoryAdd')).to.have.length(1);
//
//         wrapper.find('#mealCategoryAdd').simulate('submit');
//     })
// })
//
// describe('<MealCategoryEdit> component',()=>{
//     afterEach(() => {
//         localStorage.clear();
//     });
//     const spyEditMealCategory=chai.spy.on(App.prototype,'editMealCategory');
//     const selectedCategory={
//         "id":1,
//         "name":"Seafood",
//         "imageUrl":"https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png",
//         "numberOfMeals":1
//     };
//
//
//     it('mount <MealCategoryEdit> with no credentials',()=>{
//         const wrapper=mount(<Router>
//             <MealCategoryEdit onEditMealCategory={spyEditMealCategory} selectedCategory={selectedCategory} />
//         </Router>)
//
//         expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
//     })
//
//     it('mount <MealCategoryEdit> component',()=>{
//         localStorage.setItem("userRole","ADMIN");
//         const wrapper=mount(<Router>
//             <MealCategoryEdit onEditMealCategory={spyEditMealCategory} selectedCategory={selectedCategory} />
//         </Router>)
//
//         expect(wrapper.find('#editMealCategoryName')).to.have.length(1)
//         expect(wrapper.find('#editMealCategoryImageUrl')).to.have.length(1)
//
//         const name=wrapper.find('#editMealCategoryName')
//         const image=wrapper.find('#editMealCategoryImageUrl')
//
//         const event1 = {target: {name: "name", value: "Test meal category"}};
//         name.simulate('change',event1);
//
//         const event2 = {target: {name: "imageUrl", value: "testMealCategoryImage"}};
//         image.simulate('change',event2)
//
//         expect(wrapper.find('#mealCategoryEdit')).to.have.length(1);
//
//         wrapper.find('#mealCategoryEdit').simulate('submit');
//     })
//
//     it('mount <MealCategoryEdit> component with no edit data',()=>{
//         localStorage.setItem("userRole","ADMIN");
//         const wrapper=mount(<Router>
//             <MealCategoryEdit onEditMealCategory={spyEditMealCategory} selectedCategory={selectedCategory} />
//         </Router>)
//
//         expect(wrapper.find('#editMealCategoryName')).to.have.length(1)
//         expect(wrapper.find('#editMealCategoryImageUrl')).to.have.length(1)
//
//
//         expect(wrapper.find('#mealCategoryEdit')).to.have.length(1);
//         wrapper.find('#mealCategoryEdit').simulate('submit');
//     })
// })