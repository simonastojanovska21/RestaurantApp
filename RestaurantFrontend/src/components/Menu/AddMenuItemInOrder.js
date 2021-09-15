import React, {Component} from "react";


class AddMenuItemInOrder extends Component{

    constructor(props) {
        super(props);
        this.state={
            quantity:1
        }
    }

    render() {
        return(
            <form id={"addItemToOrder"} className={"row d-flex justify-content-center"} onSubmit={this.onFormSubmit}>
                <div className={"col-md-7"}>
                    <div className="input-group mb-3">
                        <button name="quantity" className={"btn btn-minus minusButton-"+this.props.mealId} type="button" onClick={this.handleMinusClick} style={{backgroundColor: '#bb9c57', color: '#004332'}} >
                            <i className="fa fa-minus"/>
                        </button>
                        <input type="text" className={"form-control text-center quantityField-"+this.props.mealId}
                               value={this.state.quantity} name="quantity" id="quantity"/>
                        <button name="quantity" className={"btn btn-plus plusButton-"+this.props.mealId} type="button" onClick={this.handlePlusClick} style={{backgroundColor: '#bb9c57', color: '#004332'}}>
                            <i className="fa fa-plus"/>
                        </button>
                    </div>
                </div>
                <div className={"col-md-4"}>
                    <div className={"d-grid gap-2 "}>
                        <button type="submit" className={"btn add addButton-"+this.props.mealId} style={{backgroundColor: '#004332', color: '#bb9c57'}}>
                            <i className="fa fa-cart-plus"/>Add
                        </button>
                    </div>
                </div>

            </form>
        )
    }

    onFormSubmit=(e)=>{
        e.preventDefault()

        this.props.onAddItemToOrder(this.state.quantity,this.props.mealId);
    }

    handleMinusClick=(e)=>{
        e.preventDefault();
        /* istanbul ignore else */
        if(this.state.quantity>1)
        {
            this.setState({
                quantity: (this.state.quantity-1)
            })
        }
    }

    handlePlusClick=(e)=>{
        e.preventDefault();
        /* istanbul ignore else */
        if(this.state.quantity<7)
        {
            this.setState({
                quantity: (this.state.quantity+1)
            })
        }

    }

}

export default AddMenuItemInOrder;