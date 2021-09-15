import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';

import MealCategoryList from "../components/MealCategory/mealCategoryList";
import MealCategoryAdd from "../components/MealCategory/mealCategoryAdd";
import MealCategoryEdit from "../components/MealCategory/mealCategoryEdit";
import {BrowserRouter as Router, Link} from 'react-router-dom';


const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe('<MealCategoryList> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });
    const spyEdit=chai.spy.on(App.prototype,'getMealCategory');
    const spyDelete=chai.spy.on(App.prototype,'deleteMealCategory');
    it('mount <MealCategoryList> component with no credentials',()=>{
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


        const wrapper=mount(<Router>

            <MealCategoryList mealCategories={mealCategories}
                              onEditMealCategory={spyEdit}
                              onDeleteMealCategory={spyDelete}/>
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <MealCategoryList> component',()=>{
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
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>

            <MealCategoryList mealCategories={mealCategories}
                              onEditMealCategory={spyEdit}
                              onDeleteMealCategory={spyDelete}/>
        </Router>)

        expect(wrapper.find('.mealCategoriesRow')).to.have.length(11);
        wrapper.find('.mealCategoryEdit-1').at(1).simulate('click')
        wrapper.find('.mealCategoryDelete-2').at(1).simulate('click')
    })
})

describe('<MealCategoryAdd> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });
    const spyAdd=chai.spy.on(App.prototype,'addMealCategory');

    it('mount <MealCategoryAdd> with no credentials',()=>{

        const wrapper=mount(<Router>
            <MealCategoryAdd onAddMealCategory={spyAdd} />
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <MealCategoryAdd> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MealCategoryAdd onAddMealCategory={spyAdd} />
        </Router>)

        expect(wrapper.find('#mealCategoryName')).to.have.length(1)
        expect(wrapper.find('#mealCategoryImageUrl')).to.have.length(1)

        const name=wrapper.find('#mealCategoryName')
        const image=wrapper.find('#mealCategoryImageUrl')

        const event1 = {target: {name: "name", value: "Test meal category"}};
        name.simulate('change',event1);

        const event2 = {target: {name: "imageUrl", value: "testMealCategoryImage"}};
        image.simulate('change',event2)

        expect(wrapper.find('#mealCategoryAdd')).to.have.length(1);

        wrapper.find('#mealCategoryAdd').simulate('submit');
    })
})

describe('<MealCategoryEdit> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });
    const spyEditMealCategory=chai.spy.on(App.prototype,'editMealCategory');
    const selectedCategory={
            "id":1,
            "name":"Seafood",
            "imageUrl":"https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png",
            "numberOfMeals":1
        };


    it('mount <MealCategoryEdit> with no credentials',()=>{
        const wrapper=mount(<Router>
            <MealCategoryEdit onEditMealCategory={spyEditMealCategory} selectedCategory={selectedCategory} />
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <MealCategoryEdit> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MealCategoryEdit onEditMealCategory={spyEditMealCategory} selectedCategory={selectedCategory} />
        </Router>)

        expect(wrapper.find('#editMealCategoryName')).to.have.length(1)
        expect(wrapper.find('#editMealCategoryImageUrl')).to.have.length(1)

        const name=wrapper.find('#editMealCategoryName')
        const image=wrapper.find('#editMealCategoryImageUrl')

        const event1 = {target: {name: "name", value: "Test meal category"}};
        name.simulate('change',event1);

        const event2 = {target: {name: "imageUrl", value: "testMealCategoryImage"}};
        image.simulate('change',event2)

        expect(wrapper.find('#mealCategoryEdit')).to.have.length(1);

        wrapper.find('#mealCategoryEdit').simulate('submit');
    })

    it('mount <MealCategoryEdit> component with no edit data',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <MealCategoryEdit onEditMealCategory={spyEditMealCategory} selectedCategory={selectedCategory} />
        </Router>)

        expect(wrapper.find('#editMealCategoryName')).to.have.length(1)
        expect(wrapper.find('#editMealCategoryImageUrl')).to.have.length(1)


        expect(wrapper.find('#mealCategoryEdit')).to.have.length(1);
        wrapper.find('#mealCategoryEdit').simulate('submit');
    })
})