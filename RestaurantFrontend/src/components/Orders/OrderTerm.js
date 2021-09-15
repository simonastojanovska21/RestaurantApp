import React, {Component} from "react";
import OrderService from "../../services/OrderService";


class OrderedItems extends Component{
    constructor(props) {
        super(props);
        this.state={
            order:this.props.order,
            items:[]
        }
    }

    render() {
        console.log(this.state.order.id)
        return(

                <td>
                    <ul className={"dashedList text-start"}>
                        {this.state.items.map((term)=>{
                            return(
                                <li className={"orderedMeal"}>{term.orderItemForMeal.name} x {term.quantity}</li>
                            )
                        })}
                    </ul>
                </td>

        )
    }

    componentDidMount() {
        this.loadOrderItemsForOrder(this.props.order.id)
    }

    loadOrderItemsForOrder=(id)=>{
        OrderService.getOrderItemsForOrder(id)
            .then((data)=>{
                this.setState({
                    items:data.data
                })
            })
    }

}

export default OrderedItems;