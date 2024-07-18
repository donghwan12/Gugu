const apply_seeeker_btn = document.querySelectorAll('.apply_seeeker_btn');

apply_seeeker_btn.forEach(btn=>{
    btn.addEventListener('click',function(){

        const recruit_id =  btn.getAttribute('data-recruitid');

        axios.get(`/apply/offer/list?recruit_id=${recruit_id}`)
        .then(resp=>{

            console.log(resp);

            const modalBtn = document.querySelector('.apply_user_modal_btn');

            createModalHeaderEls(resp.data,recruit_id);

            modalBtn.click();

         })
        .catch(err=>{console.log(err);})



    })
})


function createModalHeaderEls(array,recruit_id){
    //기존 노드 삭제
    const modalHeader = document.querySelector('.modal-content .modal-header .user-btn-block');
    while (modalHeader.firstChild) {
            modalHeader.removeChild(modalHeader.firstChild);
    }
    //
    const status = document.querySelector('.modal .modal-body .status');
    const title = document.querySelector('.modal .table .title');
    const name =document.querySelector('.modal .table .name');
    const email=document.querySelector('.modal .table .email');
    const tel=document.querySelector('.modal .table .tel');
    const schoolName=document.querySelector('.modal .table .schoolName');
    const major=document.querySelector('.modal .table .major');
    const graduationYear=document.querySelector('.modal .table .graduationYear');
    const summary = document.querySelector('.modal .table .summary-block');
    const filePath = document.querySelector('.file_path')

    const metting = document.querySelector('.metting');
    metting.setAttribute('data-recruit-id',recruit_id);


    console.log("summary",summary);
    array.forEach(el=>{
        const li = document.createElement('li');
        const a= document.createElement('a');
        a.innerHTML=el.resume.name;
        a.setAttribute('href',"javascript:void(0)");
        a.setAttribute('style',"display:block;");
        a.setAttribute('data-applyid',el.apply_id);
        a.classList.add('apply_user_resume_btn');
        a.addEventListener('click',function(){
                console.log("clicked...");


            title.innerHTML = el.resume.title;
            name.innerHTML = el.resume.name;
            email.innerHTML = el.resume.email;
            tel.innerHTML=el.resume.phone;
            schoolName.innerHTML=el.resume.schoolName;
            major.innerHTML=el.resume.major;
            graduationYear.innerHTML = el.resume.graduationYear;
            summary.value = el.resume.summary;
            filePath.src=el.resume.filePath;
            metting.setAttribute('data-resume-id',el.resume.id);

            axios.get('/apply/offer/carrer?resume_id='+el.resume.id)
            .then(resp=>{

                 const carrerSub = document.querySelectorAll('.carrer-sub');
                 carrerSub.forEach(el=>{
                    el.remove();
                 })
                //console.log(resp);
                const data = resp.data;
                data.forEach(el=>{
                    console.log(el);
                    const companyName = el.companyName;
                    const position = el.position;
                    const startDate = el.startDate;
                    const endDate = el.endDate;

                    const tr = document.createElement('tr');
                    tr.classList.add('carrer-sub');
                    const td1 = document.createElement('td');
                    td1.innerHTML = companyName;
                    const td2 = document.createElement('td');
                    td2.innerHTML = position;
                    const td3 = document.createElement('td');
                    td3.innerHTML = startDate.join('-');
                    const td4 = document.createElement('td');
                    td4.innerHTML = endDate.join('-');

                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);

                    const tableEl = document.querySelector('.modal .table tbody');
                    const carrer = document.querySelector('.carrer')
                    console.log(tableEl);

                    console.log(companyName,position,startDate,endDate);
                    tableEl.insertBefore(tr, carrer.nextSibling);

                })
            })
            .catch(err=>{console.log(err);});

            //자격증 가져오기
            axios.get('/apply/offer/certification?resume_id='+el.resume.id)
            .then(resp=>{
                 const certificationSub = document.querySelectorAll('.certification-sub');
                 certificationSub.forEach(el=>{
                    el.remove();
                 })
                console.log(resp);
                const data = resp.data;
                data.forEach(el=>{
                    console.log("!!!!",el);

                    const certificationName = el.certificationName;
                    const certificationDate = el.certificationDate;

                    const tr = document.createElement('tr');
                    tr.classList.add('certification-sub');
                    const td1 = document.createElement('td');
                    td1.setAttribute('colspan','2');
                    td1.innerHTML = certificationName;
                    const td2 = document.createElement('td');
                    td2.setAttribute('colspan','2');

                    td2.innerHTML = certificationDate.join('-');

                    tr.appendChild(td1);
                    tr.appendChild(td2);

                    const tableEl = document.querySelector('.modal .table tbody');
                    const certification = document.querySelector('.certification')
                    console.log(tableEl);

                    tableEl.insertBefore(tr, certification.nextSibling);

                })
            })
            .catch(err=>{console.log(err);});

            //상태 가져오기
            axios.get('/apply/offer/status?resume_id='+el.resume.id)
            .then(resp=>{
                console.log(resp);
                status.innerHTML = resp.data;
            })
            .catch(err=>{console.log(err)})



        })

        li.append(a);
        modalHeader.append(li);
    })
}

//-------------------------------------------------
// 면접진행 버튼 클릭
//-------------------------------------------------
const metting =  document.querySelector('.metting');
metting.addEventListener('click',function(){
    console.log('metting.. click...');
    const recruit_id = metting.getAttribute('data-recruit-id');
    const resume_id = metting.getAttribute('data-resume-id');
    axios.get(`/apply/status/change?resume_id=${resume_id}&recruit_id=${recruit_id}&offerStatus=면접진행중&seekerStatus=면접확정`)
    .then(resp=>{
        console.log(resp);
        const offerStatus = resp.data.offerStatus;
        const status = document.querySelector('.status');
        status.innerHTML = offerStatus;
    })
    .catch(err=>{console.log(err);});
})


//-------------------------------------------------
// 채용확정 버튼 클릭
//-------------------------------------------------
const choice =  document.querySelector('.choice');
choice.addEventListener('click',function(){
    console.log('choice.. click...');
    const recruit_id = metting.getAttribute('data-recruit-id');
    const resume_id = metting.getAttribute('data-resume-id');
    axios.get(`/apply/status/change?resume_id=${resume_id}&recruit_id=${recruit_id}&offerStatus=채용확정&seekerStatus=합격`)
    .then(resp=>{
        console.log(resp);
        const offerStatus = resp.data.offerStatus;
        const status = document.querySelector('.status');
        status.innerHTML = offerStatus;
    })
    .catch(err=>{console.log(err);});
})
