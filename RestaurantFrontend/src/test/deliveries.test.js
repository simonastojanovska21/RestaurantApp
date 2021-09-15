import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';
import DeliveriesList from "../components/Deliveries/DeliveriesList";
import {BrowserRouter as Router} from "react-router-dom";


const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe('<DeliveriesList> component test',()=>{
    afterEach(() => {
         localStorage.clear();
     });
    const deliveries=[
        {"id":1,"address":"Admin address","timeForDelivery":"2021-11-02T15:30:25"},
        {"id":2,"address":"Customer2 address","timeForDelivery":"2021-10-02T17:30:25"}
    ]
    const spy = chai.spy.on(App.prototype,'finishDelivery');

    it('mount <DeliveriesList> component with no credentials',()=>{
        const wrapper=mount(<Router>
                                <DeliveriesList deliveries={deliveries}
                                                onDelivered={spy} />
                            </Router>)

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')
    })

    it('test delivery list component',()=>{

        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Router>
                                <DeliveriesList deliveries={deliveries}
                                                onDelivered={spy} />
                            </Router>)

        expect(wrapper.find('#deliveriesList')).to.have.length(1);

        expect(wrapper.find('.delivered-1')).to.have.length(1);
        wrapper.find('.delivered-1').simulate('click');
    })

})