import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';

import StripeCheckout from "react-stripe-checkout";
import MealInShoppingCart from "../components/ShoppingCart/MealInShoppingCart";
import ShoppingCart from "../components/ShoppingCart/ShoppingCart";
import CheckOutOrder from "../components/ShoppingCart/CheckOutOrder"
import StripeButton from "../components/Payment/StripeButton"
import {BrowserRouter as Router} from 'react-router-dom';

const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

const orderedItems=[
    {"id":1,"quantity":2,"itemInOrder":{"id":1,"orderedOn":"2021-07-17T23:12:50","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"admin@admin.com","password":"$2a$10$msd09pXQ5PqiOXlDVEXYMesWxkEa9RFmc6lC8kyQpfZAG7jfMa/Fy",
                "name":"Admin name","surname":"Admin surname","phoneNumber":"070123465","address":"Admin address","userRole":"ROLE_ADMIN",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_ADMIN"],"credentialsNonExpired":true,"accountNonExpired":true}},
        "orderItemForMeal":{"id":3,"name":"Meal1","description":"Meal1 description","price":50.0,
            "mealCategory":{"id":7,"name":"Pasta","imageUrl":"https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg"},
            "imageUrl":"https://www.bosscaffe.com/sites/default/files/styles/product_thumb/public/2019-04/PASTA_BOLOGNESE.jpg?itok=xlPQ0A54",
            "ingredientsForMeal":[{"id":1,"quantity":80,"name":"Cheese"},{"id":15,"quantity":85,"name":"Bacon"},
                {"id":16,"quantity":25,"name":"Ketchup"},{"id":24,"quantity":85,"name":"Spaghetti"}]}},
    {"id":2,"quantity":3,"itemInOrder":{"id":1,"orderedOn":"2021-07-17T23:12:50","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"admin@admin.com","password":"$2a$10$msd09pXQ5PqiOXlDVEXYMesWxkEa9RFmc6lC8kyQpfZAG7jfMa/Fy",
                "name":"Admin name","surname":"Admin surname","phoneNumber":"070123465","address":"Admin address","userRole":"ROLE_ADMIN",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_ADMIN"],"credentialsNonExpired":true,"accountNonExpired":true}},
        "orderItemForMeal":{"id":5,"name":"Meal3","description":"Meal3 description","price":32.0,
            "mealCategory":{"id":1,"name":"Seafood","imageUrl":"https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png"},
            "imageUrl":"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15548.jpg?ext=.jpg",
            "ingredientsForMeal":[{"id":4,"quantity":45,"name":"Pepper"},{"id":5,"quantity":45,"name":"Onion"},
                {"id":8,"quantity":233,"name":"Lettuce"},{"id":29,"quantity":23,"name":"Salmon"},{"id":31,"quantity":85,"name":"Shrimps"}]}}]

const orderItemsForDeliveryFee=[
    {"id":8,"quantity":1,"itemInOrder":{"id":4,"orderedOn":"2021-07-23T01:23:51","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"customer2@test.com","password":"$2a$10$Wqid6x520x9WqWIj7.R.luXmM9awrQexawrvZlHxGoHISL1sSdnT6",
                "name":"Customer2 name","surname":"Customer2 surname","phoneNumber":"070123465","address":"Customer2 address",
                "userRole":"ROLE_CUSTOMER","enabled":true,"accountNonLocked":true,"authorities":["ROLE_CUSTOMER"],
                "credentialsNonExpired":true,"accountNonExpired":true}},
        "orderItemForMeal":{"id":10,"name":"Meal8","description":"Meal8 description","price":10.0,
            "mealCategory":{"id":8,"name":"Burger","imageUrl":"https://tastesbetterfromscratch.com/wp-content/uploads/2016/03/Black-Bean-Burger-8-400x400.jpg"},
            "imageUrl":"https://www.waitrose.com/content/dam/waitrose/recipes/images/b/E2E_WaitroseWeekendSunshineSpecial_36117_BestBurgers.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png",
            "ingredientsForMeal":[{"id":5,"quantity":45,"name":"Onion"},{"id":8,"quantity":233,"name":"Lettuce"},{"id":15,"quantity":85,"name":"Bacon"},
                {"id":25,"quantity":85,"name":"Potato"},{"id":27,"quantity":85,"name":"Pork"}]}}]
const orderId=1;
const username="admin@admin.com"
const itemId=2;
const quantity=3;
const spyPlusOrderItem=chai.spy.on(App.prototype,'plusOrderItemQuantity');
const spyMinusOrderItem=chai.spy.on(App.prototype,'minusOrderItemQuantity');
const spyDeleteItemFromOrder=chai.spy.on(App.prototype,'deleteOrderItemFromOrder');
const spyPayForOrder=chai.spy.on(App.prototype,'payForOrder');
const spyCancelOrder=chai.spy.on(App.prototype,'cancelOrder');


describe('<ShoppingCart> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <ShoppingCart> component without credentials',()=>{

        const wrapper=mount(<Router>
            <ShoppingCart orderedItems={orderedItems}
                          orderId={orderId}
                          username={username}
                          onPlusOrderItemFromOrder={spyPlusOrderItem}
                          onMinusOrderItemFromOrder={spyMinusOrderItem}
                          onDeleteItemFromOrder={spyDeleteItemFromOrder}/>
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <ShoppingCart> component',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');
        const wrapper=mount(<Router>
            <ShoppingCart orderedItems={orderedItems}
                          orderId={orderId}
                          username={username}
                          onPlusOrderItemFromOrder={spyPlusOrderItem}
                          onMinusOrderItemFromOrder={spyMinusOrderItem}
                          onDeleteItemFromOrder={spyDeleteItemFromOrder}/>
        </Router>)

        expect(wrapper.find('.itemInShoppingCartRow')).to.have.length(2)

        wrapper.find('.deleteOrderItem-1').at(1).simulate('click')
    })

    it('mount <ShoppingCart> component without ordered items',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN" }');
        const wrapper=mount(<Router>
            <ShoppingCart orderedItems={[]}
                          orderId={orderId}
                          username={username}
                          onPlusOrderItemFromOrder={spyPlusOrderItem}
                          onMinusOrderItemFromOrder={spyMinusOrderItem}
                          onDeleteItemFromOrder={spyDeleteItemFromOrder}/>
        </Router>)

        expect(wrapper.find('#emptyShoppingCart')).to.have.length(1)
    })
})

describe('<MealInShoppingCart> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <MealInShoppingCart> component',()=>{

        const wrapper=mount(<MealInShoppingCart id={orderId} quantity={quantity}
                                                itemId={itemId}
                                                onPlusOrderItemFromOrder={spyPlusOrderItem}
                                                onMinusOrderItemFromOrder={spyMinusOrderItem} />)

        expect(wrapper.find('.plusOrderItem-2')).to.have.length(1);
        wrapper.find('.plusOrderItem-2').simulate('click')

        expect(wrapper.find('.minusOrderItem-2')).to.have.length(1);
        wrapper.find('.minusOrderItem-2').simulate('click');

    })
})

describe('<CheckOutOrder> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    it('mount <CheckOutOrder> component without credentials',()=>{

        const wrapper=mount(<Router>
            <CheckOutOrder orderId={orderId}
                           orderedItems={orderedItems}
                           username={username}
                           payForOrder={spyPayForOrder}
                           cancelOrder={spyCancelOrder}  />
        </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <CheckOutOrder> component',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN" }');
        const wrapper=mount(<Router>
            <CheckOutOrder orderId={orderId}
                           orderedItems={orderedItems}
                           username={username}
                           payForOrder={spyPayForOrder}
                           cancelOrder={spyCancelOrder}  />
        </Router>)

        expect(wrapper.find('#totalPrice')).to.have.length(1);
        expect(wrapper.find('#deliveryFee')).to.have.length(1);
        expect(wrapper.find('#totalWithDelivery')).to.have.length(1);

        wrapper.find('#cancelOrder').at(1).simulate('click');
    })

    it('mount <CheckOutOrder> component with delivery fee',()=>{
        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN" }');
        const wrapper=mount(<Router>
            <CheckOutOrder orderId={4}
                           orderedItems={orderItemsForDeliveryFee}
                           username={username}
                           payForOrder={spyPayForOrder}
                           cancelOrder={spyCancelOrder}  />
        </Router>)

        expect(wrapper.find('#totalPrice')).to.have.length(1);
        expect(wrapper.find('#deliveryFee')).to.have.length(1);
        expect(wrapper.find('#totalWithDelivery')).to.have.length(1);

        wrapper.find('#checkOutButton').at(1).simulate('click');
    })
})


