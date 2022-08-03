function send () {
    let name = document.getElementById("name").value
    let email = document.getElementById("email").value
    let phone = document.getElementById("phone_number").value
    let message = document.getElementById("message").value
  
    let emailVal = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    let phoneVal = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im

    let alertmsg = ""

    if(name == "")
        alertmsg += "Name cannot be blank.\n"
    if(!emailVal.test(email))
        alertmsg += "Invalid email.\n"
    if(!phoneVal.test(phone))
        alertmsg += "Invalid phone.\n"
    if(message == "")
        alertmsg += "Message cannot be blank.\n"
    
    if(alertmsg == ""){
        alertmsg = "Thank you " + name.split(' ')[0] + ",\n\nYour message has been sent.\n\nExpect a reply to your email: " + email
        document.getElementById("contact_form").reset()
    }else{
        alertmsg += "\nPlease try again."
    }

    alert(alertmsg)
}