    const seeker_confirm_id =  document.querySelector('.seeker_confirm_id');
    const offer_confirm_id = document.querySelector('.offer_confirm_id');

    const seekerConfirmId = document.seekerConfirmId;
    const offerConfirmId = document.offerConfirmId;

    seeker_confirm_id.addEventListener('click',function(){
       console.log('clicked..');
       offerConfirmId.classList.add('hidden');
       seekerConfirmId.classList.remove('hidden');
    })

    offer_confirm_id.addEventListener('click',function(){
       console.log('clicked..');
       seekerConfirmId.classList.add('hidden');
       offerConfirmId.classList.remove('hidden');
    })

//--------------------------------------
//
//--------------------------------------
const seeker_id_btn = document.querySelector('.seeker_id_btn');
const offer_id_btn = document.querySelector('.offer_id_btn');


seeker_id_btn.addEventListener('click',function(){
    const username = seekerConfirmId.username.value;
    const tel = seekerConfirmId.tel.value;
    const formData = new FormData();
    formData.append("username",username);
    formData.append("tel",tel);

    axios.post('/seeker/confirm_id',formData,{headers: {'Content-Type': 'www-x-form-urlencoded',},})
    .then(resp=>{
        console.log(resp);
        alert("계정명 : " ,resp.data.userid);
    })
    .catch(err=>{console.log(err);})
})


offer_id_btn.addEventListener('click',function(){
    const companyNumber = offerConfirmId.companyNumber.value;
    const companyEmail = offerConfirmId.companyEmail.value;

    const formData = new FormData();
    formData.append("companyNumber",companyNumber);
    formData.append("companyEmail",companyEmail);

    axios.post('/offer/confirm_id',formData,{headers: {'Content-Type': 'www-x-form-urlencoded',},})
    .then(resp=>{
        console.log(resp);
        alert("계정명 : " ,resp.data.userid);

    })
    .catch(err=>{console.log(err);})
})
