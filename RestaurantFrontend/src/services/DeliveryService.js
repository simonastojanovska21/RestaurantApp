import axios from "../custom-axios/axios";
/* istanbul ignore next */
const DeliveryService={
    createNewDelivery:(address,orderId)=>{
        return axios.post("/api/delivery/add",{
            "address":address,
            "orderId":orderId
        })
    },
    getInfoForDelivery:(id)=>{
        return axios.get(`/api/delivery/${id}`)
    },
    getAllDeliveries:()=>{
        return axios.get(`/api/delivery/all`)
    },
    getDeliveriesForDay:(date)=>{
        return axios.get(`/api/delivery/day/${date}`)
    },
    getRemainingDeliveriesForToday:()=>{
        return axios.get(`/api/delivery/remaining`)
    },
    finishedDelivery:(id)=>{
        return axios.get(`/api/delivery/finish/${id}`)
    },
}

export default DeliveryService