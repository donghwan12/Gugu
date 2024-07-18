
        const formData = new FormData();

       document.addEventListener('DOMContentLoaded', function() {
            const photoUpload = document.getElementById('photoUpload');
            const photoInput = document.getElementById('photo');
            const previewImage = document.getElementById('previewImage');
            const resumeAddForm = document.resumeAddForm;

            photoUpload.addEventListener('dragover', function(event) {
                event.preventDefault();
                event.stopPropagation();
                photoUpload.classList.add('dragover');
            });

            photoUpload.addEventListener('dragleave', function(event) {
                event.preventDefault();
                event.stopPropagation();
                photoUpload.classList.remove('dragover');
            });

            photoUpload.addEventListener('drop', function(event) {
                event.preventDefault();
                event.stopPropagation();
                photoUpload.classList.remove('dragover');

                const files = event.dataTransfer.files;
                if (files.length > 0) {
                    photoInput.files = files;
                    displayPreview(files[0]);
                }

                formData.append('file',files[0]);
                console.log("formData",formData);

            });

            photoUpload.addEventListener('click', function() {
                photoInput.click();
            });

            photoInput.addEventListener('change', function() {
                if (photoInput.files.length > 0) {
                    displayPreview(photoInput.files[0]);
                    formData.append('file',files[0]);
                }

            });

            function displayPreview(file) {
                const reader = new FileReader();
                reader.onload = function(event) {
                    previewImage.src = event.target.result;
                    previewImage.style.display = 'block';
                }
                reader.readAsDataURL(file);
            }
        });

//       function addCareer() {
//            const careerContainer = document.getElementById('careerContainer');
//            const index = careerContainer.children.length;
//            const newCareer = document.createElement('div');
//            newCareer.classList.add('carrer-block')
//            newCareer.innerHTML = `
//                <div class="career-item">
//                    <label for="companyName_${index}">회사이름:</label>
//                    <input type="text" id="companyName_${index}" class="form-control" name="careers[${index}].companyName" required />
//                </div>
//                <div class="career-item">
//                    <label for="position_${index}">직책:</label>
//                    <input type="text" id="position_${index}" class="form-control" name="careers[${index}].position" required />
//                </div>
//                <div class="career-item">
//                    <label for="startDate_${index}">근무시작일:</label>
//                    <input type="datetime-local" style="width : 150px;" class="form-control w-100" id="startDate_${index}" name="careers[${index}].startDate" required />
//                </div>
//                <div class="career-item">
//                    <label for="endDate_${index}">근무종료일:</label>
//                    <input type="datetime-local"  style="width : 150px;" class="form-control w-100" id="endDate_${index}" name="careers[${index}].endDate" required />
//                </div>
//                <div class="career-item" style="align-items:right;">
//                    <label>Del:</label>
//                    <button class='btn-style' onclick='removeCarrerEl(this)'>-</button>
//                </div>
//                <hr>
//            `;
//            careerContainer.appendChild(newCareer);
//
//            // Add hidden input field to include careerList index
//            const careerIndexInput = document.createElement('input');
//            careerIndexInput.type = 'hidden';
//            careerIndexInput.name = 'resume.careers[' + index + '].index';
//            careerIndexInput.value = index;
//            newCareer.appendChild(careerIndexInput);
//        }
     function addCareer() {
            const careerContainer = document.getElementById('careerContainer');
            const index = careerContainer.children.length;
            const newCareer = document.createElement('div');
            newCareer.classList.add('carrer-block')
            newCareer.innerHTML = `
                <div class="career-item">
                    <label for="companyName_${index}">회사이름:</label>
                    <input type="text" id="companyName_${index}" class="form-control companyName" name="companyName" required />
                </div>
                <div class="career-item">
                    <label for="position_${index}">직책:</label>
                    <input type="text" id="position_${index}" class="form-control position" name="position" required />
                </div>
                <div class="career-item">
                    <label for="startDate_${index}">근무시작일:</label>
                    <input type="datetime-local" style="width : 150px;" class="form-control w-100 startDate" id="startDate_${index}" name="startDate" required />
                </div>
                <div class="career-item">
                    <label for="endDate_${index}">근무종료일:</label>
                    <input type="datetime-local"  style="width : 150px;" class="form-control w-100 endDate" id="endDate_${index}" name="endDate" required />
                </div>
                <div class="career-item" style="align-items:right;">
                    <label>Del:</label>
                    <button class='btn-style' onclick='removeCarrerEl(this)'>-</button>
                </div>
                <hr>
            `;
            careerContainer.appendChild(newCareer);

            // Add hidden input field to include careerList index
            const careerIndexInput = document.createElement('input');
            careerIndexInput.type = 'hidden';
            careerIndexInput.name = 'resume.careers[' + index + '].index';
            careerIndexInput.value = index;
            newCareer.appendChild(careerIndexInput);
        }



        document.addEventListener('DOMContentLoaded', function() {
        const summaryTextarea = document.getElementById('summary');

        summaryTextarea.addEventListener('keydown', function(e) {
        if (e.key === 'Enter') {
            // Prevent default newline behavior in textarea
            e.preventDefault();

            // Insert newline character into the textarea
            const cursorPosition = this.selectionStart;
            const textBeforeCursor = this.value.substring(0, cursorPosition);
            const textAfterCursor = this.value.substring(cursorPosition);
            this.value = textBeforeCursor + "\n" + textAfterCursor;

            // Optionally, adjust textarea height dynamically based on content
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        }
    });
});



function removeCarrerEl(btn){
    const div = btn.parentNode.parentNode;
    div.remove();
}

function addCertification(){
    console.log("clicked..");

    const tbody = document.querySelector('.certificationTable tbody')
    const trEl = document.createElement('tr');
    trEl.classList.add('certification-tr');
    const td1El = document.createElement('td');
    const td2El = document.createElement('td');

    const td3El = document.createElement('td');
    td3El.setAttribute('style','text-align:right;margin:0;padding :0');


    const input1 = document.createElement('input');
    input1.classList.add('form-control');
    input1.classList.add('certificationName');
    input1.setAttribute('placeholder',"자격증 이름");
    input1.setAttribute('name','certificationName');
    const input2 = document.createElement('input');

    input2.setAttribute('type','datetime-local');
    input2.setAttribute('placeholder','취득연월일');
    input2.setAttribute('name','certificationDate');
    input2.classList.add('form-control');
    input2.classList.add('certificationDate');

    const del = document.createElement('button');
    del.innerHTML="-";
    del.classList.add('btn-style');

    del.addEventListener('click',function(){
        const tr =  del.parentNode.parentNode;
        tr.remove();
    })

    td1El.appendChild(input1);
    td2El.appendChild(input2);
    td3El.appendChild(del);

    trEl.appendChild(td1El)
    trEl.appendChild(td2El)
    trEl.appendChild(td3El)

    tbody.appendChild(trEl);

}


const submit_btn = document.querySelector('.submit_btn');

submit_btn.addEventListener('click',function(){
        console.log('clicked..');

        formData.append('title',resumeAddForm.title.value)
        formData.append('name',resumeAddForm.name.value)
        formData.append('email',resumeAddForm.email.value)
        formData.append('phone',resumeAddForm.phone.value)
        formData.append('schoolName',resumeAddForm.schoolName.value)
        formData.append('major',resumeAddForm.major.value)
        formData.append('graduationYear',resumeAddForm.graduationYear.value)
        formData.append('summary',resumeAddForm.summary.value)
        //경력
        let carrer = [];
        const carrerBlockEls = document.querySelectorAll('.carrer-block');
        carrerBlockEls.forEach(el=>{
            const companyName = el.querySelector('.companyName').value;
            const position =el.querySelector('.position').value;
            const startDate =el.querySelector('.startDate').value;
            const endDate =el.querySelector('.endDate').value;
            console.log(companyName,position,startDate,endDate);
            const obj = {
                "companyName":companyName,
                "position":position,
                "startDate":startDate,
                "endDate":endDate
                }
                carrer.push(obj);
            })
            formData.append('carrer',JSON.stringify(carrer));
              //자격증
            let certification =[];
            const certificationTrEls = document.querySelectorAll('.certification-tr');
            certificationTrEls.forEach(el=>{
                const certificationName = el.querySelector('.certificationName').value;
                const certificationDate = el.querySelector('.certificationDate').value;
                console.log(certificationName,certificationDate);
                const obj = {
                    "certificationName":certificationName,
                    "certificationDate":certificationDate,
                }
                certification.push(obj)
            })
            formData.append('certification',JSON.stringify(certification));
            axios.post('/seeker/resume/add',formData,{ headers: {'Content-Type' :'multipart/form-data' } } )
                .then(res=>{
                    console.log(res);
                    alert("업로드 완료")
                    location.href="/seeker/resume/list";
                })
        .catch(err=>{console.log(err);})

})