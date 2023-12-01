var hrefToRedirect;

function showConfirmationBox(event, link, content) {
	event.preventDefault(); // Ngăn chặn sự kiện mặc định của thẻ a

	// Lưu href của thẻ a được click
	hrefToRedirect = link.href;

	// Hiển thị thẻ div với id="box-confirm"
	document.getElementById('box-confirm').style.display = 'block';
	document.getElementById('test').innerHTML = content;
}

function confirmAndRedirect() {
	// Gọi hàm submit của biểu mẫu cụ thể (nếu cần)

	// Chuyển hướng đến href đã lưu trữ
	window.location.href = hrefToRedirect;
}

function offNoti(choose) {
	var confirm = document.getElementById("box-confirm")
	var alert = document.getElementById("box-alert")

	if (choose == 'alert')
		alert.style.display='none'
	else {
		confirm.style.display='none'
	}
}