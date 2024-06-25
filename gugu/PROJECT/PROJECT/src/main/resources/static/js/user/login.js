/**
 * 
 */

 const seeker_login =  document.querySelector('.seeker_login');
 const offer_login = document.querySelector('.offer_login');
 
 const seekerLogin = document.seekerLogin;
 const offerLogin = document.offerLogin;
 
 seeker_login.addEventListener('click',function(){
	offerLogin.classList.add('hidden');
	seekerLogin.classList.remove('hidden');
 })
 
 offer_login.addEventListener('click',function(){
	seekerLogin.classList.add('hidden');
	offerLogin.classList.remove('hidden');
 })
