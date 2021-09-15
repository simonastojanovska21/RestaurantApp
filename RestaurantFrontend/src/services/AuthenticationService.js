import axios from "../custom-axios/axios";
import jwt from 'jwt-decode';

/* istanbul ignore next */
const authenticationService={
    loginUser:(username, password)=>{
        return axios.post("/login",{
            "username":username,
            "password":password
        })
        .then(response => {
                //console.log(response.data)
                let token=response.data;
                let decoded=jwt(token);
                //console.log(decoded.sub)
                localStorage.setItem("user",decoded.sub);
                //console.log(localStorage.getItem("user"));
                return response.data;
        })
    },
    registerUser:(username, password,repeatedPassword,name,surname,phoneNumber,address)=>{
        return axios.post("/api/auth/register",{
            "username":username,
            "password":password,
            "repeatedPassword":repeatedPassword,
            "name":name,
            "surname":surname,
            "phoneNumber":phoneNumber,
            "address":address
        })
    },
    logout() {
        localStorage.removeItem("user");
    },
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    },
    getInfoAboutUser(username){
        return axios.get(`/api/auth/userInfo/${username}`)
    },
    leaveAReview(stars,description,username){
        return axios.post("/api/auth/review",{
            "stars":stars,
            "description":description,
            "username":username
        })
    }
}

export  default authenticationService;