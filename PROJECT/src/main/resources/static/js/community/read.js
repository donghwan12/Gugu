

document.addEventListener("DOMContentLoaded", function(){

    //댓글목록 받아오기
    const no = document.querySelector('.community_no');
    console.log("cno",no.value);
    axios
    .get(`/community/reply/get/${no.value}`)
    .then(resp=>{
       console.log(resp);
       resp.data.list.forEach(el=>{
            console.log(el);
            createItems(el);
       })

    })
    .catch(err=>{console.log(err);})

    //추가버튼

    const replyBtn = document.querySelector('.reply_btn');
    replyBtn.addEventListener('click',function(){
        const no = document.querySelector('.community_no');
        const areaBox = document.querySelector('.area-box');

        console.log('areaBox',areaBox,"no",no);
         axios
         .get(`/community/reply/add?cno=${no.value}&content=${areaBox.value}`)
         .then(resp=>{
            console.log(resp);
            alert('댓글 등록 완료!');
            location.reload();
         })
         .catch(err=>{console.log(err);})
    })

})

//--

function createItems(item){
        // 새로운 div 요소를 생성합니다.
        var newItem = document.createElement('div');
        newItem.classList.add('item');
        newItem.setAttribute('data-rno',item.rno);

        // hr 요소를 생성하여 newItem에 추가합니다.
        var hr = document.createElement('hr');
        newItem.appendChild(hr);

        // content div 요소를 생성합니다.
        var contentDiv = document.createElement('div');
        contentDiv.classList.add('content');

        var itemContentDiv = document.createElement('div');
        itemContentDiv.textContent = item.content
        contentDiv.appendChild(itemContentDiv);

        // content div 내부의 각각의 문장을 생성하고 추가합니다.
//        var sentences = [
//            "안녕하세요! 반도체 품질 직무 프로 털보아저씨입니다.",
//            "보상 받기는 어려울 것 같습니다.. 인사담당자의 실수였기 때문에 추후 면접 일정을 잡을 때는 멘티님의 일정에 맞추어 진행해 달라고",
//            "요청하시는 정도는 가능할 것으로 보입니다. 연차까지 쓰고 가셨는데... 고생 많이 하셨습니다 ㅠㅠ"
//        ];
//
//        sentences.forEach(function(sentence) {
//            var div = document.createElement('div');
//            div.textContent = sentence;
//            contentDiv.appendChild(div);
//        });


        // contentDiv를 newItem에 추가합니다.
        newItem.appendChild(contentDiv);

        // foot div 요소를 생성합니다.
        var footDiv = document.createElement('div');
        footDiv.classList.add('foot');

        // foot 내부의 첫 번째 섹션을 생성하고 추가합니다.
        var section1 = document.createElement('div');
        var thumbUpLink = document.createElement('a');
        thumbUpLink.href = '#'; // 공감하기 링크의 URL을 설정합니다.
        var thumbUpIcon = document.createElement('span');
        thumbUpIcon.classList.add('material-symbols-outlined');
        thumbUpIcon.textContent = 'thumb_up';
        var thumbUpText = document.createElement('span');
        thumbUpText.textContent = '공감하기';
        thumbUpLink.appendChild(thumbUpIcon);
        thumbUpLink.appendChild(thumbUpText);
        section1.appendChild(thumbUpLink);

        var commentLink = document.createElement('a');
        commentLink.href = '#'; // 대댓글달기 링크의 URL을 설정합니다.
        var commentIcon = document.createElement('span');
        commentIcon.classList.add('material-symbols-outlined');
        commentIcon.textContent = 'chat_bubble';
        var commentText = document.createElement('span');
        commentText.textContent = '대댓글달기';
        commentLink.appendChild(commentIcon);
        commentLink.appendChild(commentText);
        section1.appendChild(commentLink);

        footDiv.appendChild(section1);

        // foot 내부의 두 번째 섹션을 생성하고 추가합니다.
        var section2 = document.createElement('div');
        var accountNameSpan = document.createElement('span');
        accountNameSpan.textContent = (item.username==null)?'테스트계정':item.username;
        section2.appendChild(accountNameSpan);
        section2.appendChild(document.createTextNode('\u00A0\u00A0|\u00A0\u00A0')); // &nbsp;를 텍스트 노드로 추가합니다.
        var timestampSpan = document.createElement('span');
        let yyyymmddhhMMss=null;
        if(item.regdate!=null){
           yyyymmddhhMMss = `${item.regdate[0]}-${item.regdate[1]}-${item.regdate[2]} ${item.regdate[3]}:${item.regdate[4]}:${item.regdate[5]}`
        }

        timestampSpan.textContent = (item.regdate==null)?'2024-01-01 HH:MM:SS':yyyymmddhhMMss; // 실제 날짜 및 시간을 설정합니다.
        section2.appendChild(timestampSpan);

        footDiv.appendChild(section2);

        // footDiv를 newItem에 추가합니다.
        newItem.appendChild(footDiv);

        // 생성한 newItem을 문서의 적절한 위치에 추가합니다.
        var container = document.querySelector('.items'); // 적절한 컨테이너 요소를 선택합니다.
        container.appendChild(newItem);

}