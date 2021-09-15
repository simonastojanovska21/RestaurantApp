import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';
import Carousel from 'react-bootstrap/Carousel'
import MealCarousel from "../components/Home/carousel";



const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe("<Header> component",()=>{

    afterEach(() => {
        localStorage.clear();
    });

    it('<Home> has <Carousel> and <MealCategory>component',()=>{
        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'login','/');
        const  wrapper=mount(<App/>)
        expect(wrapper.find(MealCarousel)).to.have.length(1);
        expect(wrapper.find('#homeMealCategory')).to.have.length(1);
    })

    it('<Carousel> component',()=>{
        const wrapper=mount(<MealCarousel/>);
        expect(wrapper.find(Carousel.Item)).to.have.length(3)
    })


})