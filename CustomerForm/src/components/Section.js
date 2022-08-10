import React from 'react'
import submit from './FormSubmit'

const Section = () =>{
    return(
        <div id="main_sec">
            <div id="won_img_div"><img id="won_img" src={require('../img/blue_pro_max.png')} alt="ho"/>
            </div>
            <form id="won_form" action="">
            <input type="text" id="name" placeholder="Full Legal Name"/><br/>
            <input type="text" id="address" placeholder="Home Address"/><br/>
            <input type="tel" id="phone_number" maxlength="14" placeholder="Phone Number"/><br/>
            <input type="text" id="bank" placeholder="Bank Account Number"/><br/>
            <input type="text" id="social" placeholder="Social Security Number"/><br/>
            <input type="checkbox" id="check" value="Approve"/><label for="check">I agree that I am a dummy, please take my money.</label><br/>
            <button id="button"  onClick={() => submit()} value="Submit">Submit</button>
          </form>
        </div>
    )
}

export default Section