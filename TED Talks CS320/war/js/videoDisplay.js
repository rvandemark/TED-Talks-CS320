var initSubmitBtn = document.getElementById("initReviewSubmit");
var popUp         = document.getElementById("reviewPopUp");
var background    = document.getElementById("reviewPopUpBackground");
var closeBtn      = document.getElementById("closeReview");

initSubmitBtn.onclick = function() {
	background.style.display = "block";
	popUp.style.display = "block";
}
closeBtn.onclick = function() {
	background.style.display = "none";
	popUp.style.display = "none";
}

function goToURL(link) {
	window.location = link;
}