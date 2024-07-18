
function addCareerField() {
     var table = document.querySelector('.table'); // 테이블 요소 가져오기
     var careerRows = table.querySelectorAll('.career-tr'); // 경력 행(tr) 모두 가져오기
     var lastCareerRow = careerRows[careerRows.length - 1]; // 마지막 경력 행(tr) 가져오기

     var careerRow = document.createElement('tr'); // 새로운 행(tr) 생성
     careerRow.classList.add("career-tr");
     // 경력 필드 셀 추가
     var careerLabelCell = document.createElement('td');
     careerLabelCell.className = 'text-light';
     careerLabelCell.style.backgroundColor = '#00a8ff';
     careerLabelCell.textContent = '경력';
     careerRow.appendChild(careerLabelCell);

     var careerInputCell = document.createElement('td');
     careerInputCell.colSpan = 2;
     careerInputCell.id = 'career-field';
     careerInputCell.innerHTML = '<input class="form-control w-100" name="career" required type="text">';
     careerRow.appendChild(careerInputCell);

     // 삭제 버튼 셀 추가
     var deleteCell = document.createElement('td');
     deleteCell.style.textAlign = 'right';
     deleteCell.innerHTML = '<a class="btn btn-secondary" href="javascript:void(0)" onclick="removeCareerField(this)">-</a>';
     careerRow.appendChild(deleteCell);

     // 새로운 행(tr)을 career-tr 아래에 추가
     lastCareerRow.parentNode.insertBefore(careerRow, lastCareerRow.nextSibling);
}

function removeCareerField(button) {
    var rowToRemove = button.parentNode.parentNode; // 버튼의 부모(td)의 부모(tr) 요소 가져오기
    rowToRemove.remove(); // 해당 행(tr) 삭제
}



document.addEventListener('DOMContentLoaded', function () {
    const checkboxes = document.querySelectorAll('.salary-options input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            if (this.checked) {
                checkboxes.forEach(box => {
                    if (box !== this) {
                        box.checked = false;
                    }
                });
            }
        });
    });
});

function execDaumPostcode() {
    var elementLayer = document.getElementById('layer');
    var addressContainer = document.getElementById('layerContainer');

    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }
            document.getElementById('jobzone').value = addr;
            elementLayer.style.display = 'none';
        },
        width: '100%',
        height: '100%'
    }).embed(addressContainer);

    elementLayer.style.display = 'block';
    initLayerPosition();
}

function closeDaumPostcode() {
    var elementLayer = document.getElementById('layer');
    elementLayer.style.display = 'none';
}

function initLayerPosition() {
    var width = 300;
    var height = 400;
    var borderWidth = 2;
    var elementLayer = document.getElementById('layer');
    elementLayer.style.width = width + 'px';
    elementLayer.style.height = height + 'px';
    elementLayer.style.border = borderWidth + 'px solid';
    elementLayer.style.left = ((window.innerWidth - width) / 2 - borderWidth) + 'px';
    elementLayer.style.top = ((window.innerHeight - height) / 2 - borderWidth) + 'px';
}