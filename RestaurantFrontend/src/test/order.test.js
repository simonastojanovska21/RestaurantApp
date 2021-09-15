import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';

import OrderTerm from "../components/Orders/OrderTerm";
import OrderList from "../components/Orders/OrderList";

import {BrowserRouter as Router} from 'react-router-dom';

const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe('<OrderList> component',()=>{
    afterEach(() => {
        localStorage.clear();
    });

    const orders=[
        {"id":1,"orderedOn":"2021-07-17T23:12:50","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"admin@admin.com","password":"$2a$10$msd09pXQ5PqiOXlDVEXYMesWxkEa9RFmc6lC8kyQpfZAG7jfMa/Fy",
                "name":"Admin name","surname":"Admin surname","phoneNumber":"070123465","address":"Admin address","userRole":"ROLE_ADMIN",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_ADMIN"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":2,"orderedOn":"2021-07-17T20:14:26","orderStatus":"PROCESSING",
            "orderedByUser":{"username":"admin@admin.com","password":"$2a$10$msd09pXQ5PqiOXlDVEXYMesWxkEa9RFmc6lC8kyQpfZAG7jfMa/Fy",
                "name":"Admin name","surname":"Admin surname","phoneNumber":"070123465","address":"Admin address","userRole":"ROLE_ADMIN",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_ADMIN"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":3,"orderedOn":"2021-07-17T19:50:50","orderStatus":"DELIVERING",
            "orderedByUser":{"username":"admin@admin.com","password":"$2a$10$msd09pXQ5PqiOXlDVEXYMesWxkEa9RFmc6lC8kyQpfZAG7jfMa/Fy",
                "name":"Admin name","surname":"Admin surname","phoneNumber":"070123465","address":"Admin address","userRole":"ROLE_ADMIN",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_ADMIN"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":4,"orderedOn":"2021-07-23T01:23:51","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"customer2@test.com","password":"$2a$10$Wqid6x520x9WqWIj7.R.luXmM9awrQexawrvZlHxGoHISL1sSdnT6",
                "name":"Customer2 name","surname":"Customer2 surname","phoneNumber":"070123465","address":"Customer2 address","userRole":"ROLE_CUSTOMER",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_CUSTOMER"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":5,"orderedOn":"2021-07-23T01:23:05","orderStatus":"PROCESSING",
            "orderedByUser":{"username":"customer2@test.com","password":"$2a$10$Wqid6x520x9WqWIj7.R.luXmM9awrQexawrvZlHxGoHISL1sSdnT6",
                "name":"Customer2 name","surname":"Customer2 surname","phoneNumber":"070123465","address":"Customer2 address","userRole":"ROLE_CUSTOMER",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_CUSTOMER"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":6,"orderedOn":"2021-07-23T00:54:21","orderStatus":"DELIVERING",
            "orderedByUser":{"username":"customer2@test.com","password":"$2a$10$Wqid6x520x9WqWIj7.R.luXmM9awrQexawrvZlHxGoHISL1sSdnT6",
                "name":"Customer2 name","surname":"Customer2 surname","phoneNumber":"070123465","address":"Customer2 address","userRole":"ROLE_CUSTOMER",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_CUSTOMER"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":7,"orderedOn":"2021-07-30T01:23:51","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"customer1@test.com","password":"$2a$10$saNUElbe9SEHvUHMztk9VuaV5FtjMvbSXHOVc4ZBoAmgYRv3mcO2K",
                "name":"Customer1 name","surname":"Customer1 surname","phoneNumber":"070123465","address":"Customer1 address","userRole":"ROLE_CUSTOMER",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_CUSTOMER"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":8,"orderedOn":"2021-07-25T01:23:05","orderStatus":"PROCESSING",
            "orderedByUser":{"username":"customer1@test.com","password":"$2a$10$saNUElbe9SEHvUHMztk9VuaV5FtjMvbSXHOVc4ZBoAmgYRv3mcO2K",
                "name":"Customer1 name","surname":"Customer1 surname","phoneNumber":"070123465","address":"Customer1 address","userRole":"ROLE_CUSTOMER",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_CUSTOMER"],"credentialsNonExpired":true,"accountNonExpired":true}},
        {"id":9,"orderedOn":"2021-08-07T20:00:15.480577","orderStatus":"ACTIVE",
            "orderedByUser":{"username":"employee@test.com","password":"$2a$10$Z54huaqv0.ZUn.9KmccWB.UblHEgy0IBP4JLjEs18BA6busPgKoZy",
                "name":"Employee name","surname":"Employee surname","phoneNumber":"070123465","address":"Employee address","userRole":"ROLE_EMPLOYEE",
                "enabled":true,"accountNonLocked":true,"authorities":["ROLE_EMPLOYEE"],"credentialsNonExpired":true,"accountNonExpired":true}}]
    const spyOnReadyForDelivery=chai.spy.on(App.prototype,'createNewDelivery');

    it('mount <OrderList> component with no credentials',()=>{
        const wrapper=mount(<Router>
                    <OrderList orders={orders}
                               onReadyForDelivery={spyOnReadyForDelivery}/>
                    </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('mount <OrderList> component',()=>{
        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
            <OrderList orders={orders}
                       onReadyForDelivery={spyOnReadyForDelivery}/>
        </Router>)

        expect(wrapper.find('.orderRow')).to.have.length(9)

        expect(wrapper.find('.readyForDelivery-1')).to.have.length(1);
        wrapper.find('.readyForDelivery-1').simulate('click');
    })
})