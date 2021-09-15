import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';
import { BrowserRouter as Router } from "react-router-dom"
import Header from "../components/Header/header";
import Login from "../components/Login/login";
const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe("<Login> component",()=>{

    afterEach(() => {
        localStorage.clear();
    });
    const spy = chai.spy.on(App.prototype,'logoutUser');
    it('test login with admin data',  ()=>{

        const wrapper=mount(<Router>
            <Login onLoginUser={spy}/>
        </Router>)

        const username = wrapper.find('#username');
        const password=wrapper.find('#password');

        const event1 = {target: {name: "username", value: "admin@admin.com"}};
        username.simulate('change',event1);

        const event2 = {target: {name: "password", value: "P@ssword"}};
        password.simulate('change',event2);

        wrapper.find('#loginData').simulate('submit');

        setTimeout(()=>{
            expect(window.location.href).to.equal('http://localhost:3000/menu')
            wrapper.unmount();
            wrapper.mount();
        },2000)
    })

    it('mount <Login> component with login error',()=>{
        localStorage.setItem("loginError","yes");
        const wrapper=mount(<Router>
            <Login onLoginUser={spy}/>
        </Router>)

        expect(wrapper.find('#invalidData')).to.have.length(1);
    })

    it('test logout button',()=>{

        localStorage.setItem("userRole","ADMIN");
        const wrapper=mount(<Header username={"admin@admin.com"} onLogoutUser={spy} />)
        expect(wrapper.find(Header)).to.have.length(1);
        expect(wrapper.find('#logoutButton')).to.have.length(1);
        wrapper.find('#logoutButton').simulate('click');
    })

    it('test logout button with employee',()=>{

        localStorage.setItem("userRole","ROLE_EMPLOYEE");
        const wrapper=mount(<Header username={"admin@admin.com"} onLogoutUser={spy} />)
        expect(wrapper.find(Header)).to.have.length(1);
        expect(wrapper.find('#logoutButton')).to.have.length(1);
        wrapper.find('#logoutButton').simulate('click');
    })

    it('test logout button with delivery user',()=>{

        localStorage.setItem("userRole","ROLE_DELIVERY");
        const wrapper=mount(<Header username={"admin@admin.com"} onLogoutUser={spy} />)
        expect(wrapper.find(Header)).to.have.length(1);
        expect(wrapper.find('#logoutButton')).to.have.length(1);
        expect(wrapper.props().onLogoutUser).to.equal(spy)
        wrapper.find('#logoutButton').simulate('click');
        expect(spy).to.have.been.called();
    })

})