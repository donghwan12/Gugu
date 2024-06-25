    const seeker_confirm_pw =  document.querySelector('.seeker_confirm_pw');
    const offer_confirm_pw = document.querySelector('.offer_confirm_pw');

    const seekerConfirmPw = document.seekerConfirmPw;
    const offerConfirmPw = document.offerConfirmPw;

    seeker_confirm_pw.addEventListener('click',function(){
       console.log('clicked..');
       offerConfirmPw.classList.add('hidden');
       seekerConfirmPw.classList.remove('hidden');
    })

    offer_confirm_pw.addEventListener('click',function(){
       console.log('clicked..');
       seekerConfirmPw.classList.add('hidden');
       offerConfirmPw.classList.remove('hidden');
    })

//--------------------------------------
//
//--------------------------------------
const seeker_pw_btn = document.querySelector('.seeker_pw_btn');
const offer_pw_btn = document.querySelector('.offer_pw_btn');


seeker_pw_btn.addEventListener('click',function(){
    const userid = seekerConfirmPw.userid.value;
    const username = seekerConfirmPw.username.value;
    const tel = seekerConfirmPw.tel.value;
    const formData = new FormData();
    formData.append("userid",userid);
    formData.append("username",username);
    formData.append("tel",tel);

    axios.post('/seeker/confirm_pw',formData,{headers: {'Content-Type': 'www-x-form-urlencoded',},})
    .then(resp=>{
        console.log(resp);
        alert(resp.data.message);
    })
    .catch(err=>{console.log(err);})
})


offer_pw_btn.addEventListener('click',function(){
    const userid = offerConfirmPw.userid.value;
    const companyNumber = offerConfirmPw.companyNumber.value;
    const companyEmail = offerConfirmPw.companyEmail.value;

    const formData = new FormData();
    formData.append("userid",userid);
    formData.append("companyNumber",companyNumber);
    formData.append("companyEmail",companyEmail);

    axios.post('/offer/confirm_pw',formData,{headers: {'Content-Type': 'www-x-form-urlencoded',},})
    .then(resp=>{
        console.log(resp);
        alert(resp.data.message);
    })
    .catch(err=>{console.log(err);})
})
