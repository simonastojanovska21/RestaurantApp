import axios from "../custom-axios/axios";
/* istanbul ignore next */
const OrderService={
    getAllOrders:()=>{
        return axios.get("/api/order/all");
    },
    getAllOrdersForUser:(username)=>{
        return axios.get(`/api/order/user/${username}`);
    },
    getAllProcessingOrders:()=>{
        return axios.get("/api/order/processing");
    },
    getAllDeliveringOrders:()=>{
        return axios.get("/api/order/delivering");
    },
    payForOrder:(id)=>{
        return axios.get(`/api/order/pay/${id}`)
    },
    cancelOrder:(username)=>{
        return axios.get(`/api/order/cancel/${username}`)
    },
    getDetailsForOrder:(id)=>{
        return axios.get(`/api/order/${id}`)
    },
    getActiveOrderForUser:(username)=>{
        return axios.get(`api/order/activeOrder/${username}`)
    },
    getTop5OrderedMeals:()=>{
        return axios.get("/api/order/topOrdered")
    },
    addOrderItemInOrder:(quantity,mealId,username)=>{
        return axios.post("/api/order/addOrderItem",{
            "quantity":quantity,
            "mealId":mealId,
            "username":username
        })
    },
    plusOrderItemQuantity:(id)=>{
        return axios.get(`/api/order/plusQuantity/${id}`)
    },
    minusOrderItemQuantity:(id)=>{
        return axios.get(`/api/order/minusQuantity/${id}`)
    },
    deleteOrderItemFromOrder:(id)=>{
        return axios.delete(`/api/order/delete/${id}`);
    },
    getOrderItemsForOrder:(id)=>{
        return axios.get(`/api/order/orderItems/${id}`);
    },
    getOrderItemsForUser:(username)=>{
        return axios.get(`/api/order/orderItems/user/${username}`);
    }
}

export default OrderService