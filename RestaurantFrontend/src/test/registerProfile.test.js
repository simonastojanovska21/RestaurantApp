import React from 'react';
import App from "../components/App/App"
import { mount } from 'enzyme';
import {expect} from 'chai';
import Profile from "../components/User/Profile";
import { BrowserRouter as Router } from "react-router-dom"
import Unauthorized from "../components/Exceptions/Unauthorized";
import Register from "../components/Register/register";

const chai = require('chai')
const spy = require('chai-spies');
chai.use(spy);

describe("<Register> component",()=> {
    const spyLeaveReview = chai.spy.on(App.prototype,'leaveReview');
    const spyRegister=chai.spy.on(App.prototype,'registerUser')
    afterEach(() => {
        localStorage.clear();
    });
    it('mount <Register> component',()=>{

        const wrapper=mount(<Router>
            <Register onRegisterUser={spyRegister}/>
        </Router> )

        const username = wrapper.find('#username');
        const password=wrapper.find('#password');
        const repeatedPassword = wrapper.find('#repeatedPassword');
        const name=wrapper.find('#name');
        const surname = wrapper.find('#surname');
        const phone=wrapper.find('#phoneNumber');
        const address = wrapper.find('#address');

        const eventUsername = {target: {name: "username", value: "test@test.com"}};
        username.simulate('change',eventUsername);

        const eventPassword = {target: {name: "password", value: "P@ssword"}};
        password.simulate('change',eventPassword);

        const eventRepeatedPassword = {target: {name: "repeatedPassword", value: "P@ssword"}};
        repeatedPassword.simulate('change',eventRepeatedPassword);

        const eventName = {target: {name: "name", value: "Test name"}};
        name.simulate('change',eventName);

        const eventSurname = {target: {name: "surname", value: "Test surname"}};
        surname.simulate('change',eventSurname);

        const eventPhone = {target: {name: "phoneNumber", value: "070123456"}};
        phone.simulate('change',eventPhone);

        const eventAddress = {target: {name: "address", value: "Test address"}};
        address.simulate('change',eventAddress);

        wrapper.find('#registerData').simulate('submit');


        setTimeout(()=>{
            expect(window.location.href).to.equal('http://localhost:3000/login')
            wrapper.unmount();
            wrapper.mount();
        },2000)
    })

    it('mount <Register> with passwordDoNotMatch',()=>{
        localStorage.setItem("passwordDoNotMatch","yes")
        const wrapper=mount(<Router>
            <Register onRegisterUser={spyRegister}/>
        </Router> )

        expect(wrapper.find('#passwordDoNotMatch')).to.have.length(1);
    })

    it('mount <Register> with userExists',()=>{
        localStorage.setItem("userExists","yes")
        const wrapper=mount(<Router>
            <Register onRegisterUser={spyRegister}/>
        </Router> )

        expect(wrapper.find('#userExists')).to.have.length(1);
    })

    it('mount <Profile> component without user',()=>{
        const user={
            username:'admin@admin.com',
            name:'Admin name',
            surname:'Admin surname',
            address:'Admin address',
            phoneNumber:'070123456'
        }


        const wrapper=mount(<Router>
            <Profile user={user} onLeaveReview={spyLeaveReview} />
        </Router>);

        expect(window.location.href).to.equal('http://localhost:3000/unauthorized')

    })

    it('mount <Profile> component',()=>{
        const user={
            username:'admin@admin.com',
            name:'Admin name',
            surname:'Admin surname',
            address:'Admin address',
            phoneNumber:'070123456'
        }

        localStorage.setItem("user",'{ "username":"admin@admin.com", "role":"ROLE_ADMIN"  }');
        const wrapper=mount(<Router>
            <Profile user={user} onLeaveReview={spyLeaveReview} />
        </Router>);

        expect(wrapper.find('#leaveReview')).to.have.length(1);

        const stars = wrapper.find('#fiveStar');
        const description=wrapper.find('#review');

        const event1 = {target: {name: "stars", checked:true}};
        stars.simulate('change',event1);

        const event2 = {target: {name: "description", value: "12345"}};
        description.simulate('change',event2);

        wrapper.find('#reviewForm').simulate('submit');

        expect(wrapper.find('#leftReview')).to.have.length(1);

    })



    it('Unauthorized access',()=>{

        // eslint-disable-next-line no-restricted-globals
        history.replaceState({},'unauthorized','/unauthorized');
        const wrapper=mount(<App/>)

        expect(wrapper.find(Unauthorized)).to.have.length(1)

        expect(wrapper.find('#goBack')).to.have.length(1);
        wrapper.find('#goBack').simulate('click')
    })

});