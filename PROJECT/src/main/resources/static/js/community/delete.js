
const reqDeleteBtn=document.querySelector('.req_delete_btn');
reqDeleteBtn.addEventListener('click',function(){

    const deleteForm= document.deleteForm;

    const formData = new FormData();
    formData.append('no',deleteForm.no.value);
    formData.append('pageNo',deleteForm.pageNo.value);
    formData.append('password',deleteForm.password.value);

    axios.post('/community/delete',formData, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
    .then(resp=>{
        console.log(resp);
        alert("게시글 삭제를 완료하였습니다.!");
        location.href="/community/list";
     })
    .catch(err=>{console.log(err);})


})