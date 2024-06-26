

const reqUpateBtn=document.querySelector('.req_upate_btn');
reqUpateBtn.addEventListener('click',function(){

    const formData = new FormData();
    formData.append('no',community.no);
    formData.append('category',community.category);

    const content = document.querySelector('.content-block textarea');
    formData.append('content',content.value);
    formData.append('count',community.count);
    formData.append('dirpath',community.dirpath);
    formData.append('filename',community.filename);
    formData.append('filesize',community.filesize);
    formData.append('regdate',community.regdate);
    formData.append('title',community.title);
    formData.append('username',community.username);


    axios.post('/community/update',formData, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
    .then(resp=>{
        console.log(resp);
        alert("게시글 수정을 완료하였습니다.!");
        location.href="/community/read?no=" + community.no+"&pageNo=" + pageNo;
     })
    .catch(err=>{console.log(err);})


})