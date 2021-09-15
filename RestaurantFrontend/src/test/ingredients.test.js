import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';

import IngredientList from "../components/Ingredients/IngredientList";
import IngredientEdit from "../components/Ingredients/IngredientEdit";
import IngredientAdd from "../components/Ingredients/IngredientAdd";
import {BrowserRouter as Router, Link} from 'react-router-dom';
import Unauthorized from "../components/Exceptions/Unauthorized";
const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe('<IngredientsList> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });
    const spyEdit=chai.spy.on(App.prototype,'getIngredient');
    const spyDelete=chai.spy.on(App.prototype,'deleteIngredient');
    it('mount <IngredientsList> component with no credentials',()=>{
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

        const wrapper=mount(<Router>

            <IngredientList ingredients={ingredients}
                            onEditIngredient={spyEdit}
                            onDeleteIngredient={spyDelete}/>
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <InredientsList> component',()=>{
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
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>

            <IngredientList ingredients={ingredients}
                            onEditIngredient={spyEdit}
                            onDeleteIngredient={spyDelete}/>
        </Router>)

        expect(wrapper.find('.ingredientsRow')).to.have.length(33);
        wrapper.find('.ingredientEdit-1').at(1).simulate('click')
        wrapper.find('.ingredientDelete-2').at(1).simulate('click')
    })
})

describe('<IngredientsAdd> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });
    const spyAdd=chai.spy.on(App.prototype,'addIngredient');

    it('mount <IngredientsAdd> with no credentials',()=>{

        const wrapper=mount(<Router>
            <IngredientAdd onAddIngredient={spyAdd} />
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <IngredientsAdd> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <IngredientAdd onAddIngredient={spyAdd} />
        </Router>)

        expect(wrapper.find('#ingredientName')).to.have.length(1)
        expect(wrapper.find('#ingredientQuantity')).to.have.length(1)

        const name=wrapper.find('#ingredientName')
        const quantity=wrapper.find('#ingredientQuantity')

        const event1 = {target: {name: "name", value: "Test ingredient"}};
        name.simulate('change',event1);

        const event2 = {target: {name: "quantity", value: "5"}};
        quantity.simulate('change',event2)

        expect(wrapper.find('#submitIngredient')).to.have.length(1);

        wrapper.find('#submitIngredient').simulate('submit');
    })
})

describe('<IngredientsEdit> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });
    const spyEditIngredient=chai.spy.on(App.prototype,'editIngredient');
    const selectedIngredient={
        "id":5,
        "quantity":45,
        "name":"Onion"
    }

    it('mount <IngredientsEdit> with no credentials',()=>{
        const wrapper=mount(<Router>
            <IngredientEdit onEditIngredient={spyEditIngredient} selectedIngredient={selectedIngredient} />
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <IngredientsEdit> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <IngredientEdit onEditIngredient={spyEditIngredient} selectedIngredient={selectedIngredient} />
        </Router>)

        expect(wrapper.find('#editIngredientName')).to.have.length(1)
        expect(wrapper.find('#editIngredientQuantity')).to.have.length(1)

        const name=wrapper.find('#editIngredientName')
        const quantity=wrapper.find('#editIngredientQuantity')

        const event1 = {target: {name: "name", value: "Test ingredient"}};
        name.simulate('change',event1);

        const event2 = {target: {name: "quantity", value: "5"}};
        quantity.simulate('change',event2)

        expect(wrapper.find('#editIngredient')).to.have.length(1);

        wrapper.find('#editIngredient').simulate('submit');
    })

    it('mount <IngredientsEdit> component with no edit data',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <IngredientEdit onEditIngredient={spyEditIngredient} selectedIngredient={selectedIngredient} />
        </Router>)

        expect(wrapper.find('#editIngredientName')).to.have.length(1)
        expect(wrapper.find('#editIngredientQuantity')).to.have.length(1)

        expect(wrapper.find('#editIngredient')).to.have.length(1);

        wrapper.find('#editIngredient').simulate('submit');
    })
})