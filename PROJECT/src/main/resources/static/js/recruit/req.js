const recruit_btn = document.querySelector('.recruit_btn')
recruit_btn.addEventListener('click',function(){

    axios.get("/seeker/resume/my")
    .then(resp=>{
        console.log(resp);

        resumeArr = resp.data;
        resumeArr.forEach(el=>{
            console.log(el);
            createResumeItem(el);
        })

    })
    .catch(err=>{console.log(err);})

    const modal_btn = document.querySelector('.moal_btn');
    modal_btn.click();

    //이력서 가져오기
    //axios.get('/seeker/resume/my')
})


function createResumeItem(data){
    const parent = document.querySelector('.modal-body table tbody');


    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }

    const tr = document.createElement('tr');
    const td1 = document.createElement('td');
    const td2 = document.createElement('td');
    const td3 = document.createElement('td');
    const td4 = document.createElement('td');
    const td5 = document.createElement('td');
    const td6 = document.createElement('td');

    td1.setAttribute('style','width:100px;');
    td2.setAttribute('style','');
    td3.setAttribute('style','width:100px;');
    td4.setAttribute('style','width:180px;');
    td5.setAttribute('style','width:50px;');
    td6.setAttribute('style','width:50px;');

    td1.classList.add('bg-dark');
    td1.classList.add('text-light');
    td3.classList.add('bg-dark');
    td3.classList.add('text-light');
    td5.classList.add('bg-dark');
    td5.classList.add('text-light');

    td1.innerHTML='제목'
    td2.innerHTML=data.title;
    td3.innerHTML='작성일'
    td4.innerHTML=data.creationDate.join('-');
    td5.innerHTML='선택'

    const chk = document.createElement('input')
    chk.setAttribute('type','radio');
    chk.setAttribute('name','resume_id');

    chk.setAttribute('display','none');
    chk.setAttribute('id','chk_' +data.id);
    chk.setAttribute('data-resumeid',data.id);
    const label = document.createElement('label')
    label.setAttribute('for','chk_' +data.id);//data.id는 이력서 Id

    td6.appendChild(chk);
    td6.appendChild(label);

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);

    parent.appendChild(tr);
}

const apply_modal_btn = document.querySelector('.apply_modal_btn')
apply_modal_btn.addEventListener('click',function(){
    console.log("clicked..");

    const radioEl  = document.querySelector('.modal input[name="resume_id"]:checked');
    console.log(radioEl);
    const resumeid = radioEl.getAttribute('data-resumeid');

    axios.get(`/apply/add?resume_id=${resumeid}&recruit_id=${recruit_id}`)
    .then(resp=>{
        console.log(resp);
        alert("지원완료!");
        location.href='/recruit/list';
     })
    .catch(err=>{console.log(err)});


})