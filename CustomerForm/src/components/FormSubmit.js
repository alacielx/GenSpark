function submit () {
    // <input type="text" id="name" placeholder="Full Legal Name"/><br/>
    //         <input type="email" id="address" placeholder="Home Address"/><br/>
    //         <input type="tel" id="phone_number" maxlength="14" placeholder="Phone Number"/><br/>
    //         <input type="text" id="bank" placeholder="Bank Account Number"/><br/>
    //         <input type="text" id="social" placeholder="Social Security Number"/><br/>
            
    let name = document.getElementById("name").value
    let address = document.getElementById("address").value
    let phone = document.getElementById("phone_number").value
    let bank = document.getElementById("bank").value
    let social = document.getElementById("social").value
  
    let phoneVal = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im

    let alertmsg = ""

    if(name == "")
        alertmsg += "Invalid name.\n"
    if(address == "")
        alertmsg += "Invalid address.\n"
    if(!phoneVal.test(phone))
        alertmsg += "Invalid phone.\n"
    if(bank == "")
        alertmsg += "Invalid bank account.\n"
    if(social == "")
        alertmsg += "Invalid social security.\n"
    
    if(alertmsg == ""){
        alertmsg = 
        "Thank you " + name.split(' ')[0] 
        + ",\n\nYour iPhone will be arriving soon to the address " 
        + address
        document.getElementById("won_form").reset()
    }else{
        alertmsg += "\nPlease try again."
    }

    alert(alertmsg)
}

export default submit