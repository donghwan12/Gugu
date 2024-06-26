const nextBtn = document.querySelector('section.main>.left>.category>.body .swiper .swiper-button-next');
const prevBtn = document.querySelector('section.main>.left>.category>.body .swiper .swiper-button-prev');
const swiperWrapper = document.querySelector("section.main>.left>.category>.body .swiper-wrapper");
let direction ="l";

prevBtn.addEventListener('click',function(){
      if(direction=='r'){
          swiperWrapper.style.left="0px";
          direction='l';
      }
   nextBtn.style.color="royalblue";
   prevBtn.style.color="gray";
});

nextBtn.addEventListener('click',function(){
   if(direction=='l'){
       swiperWrapper.style.left="-340px";
       direction='r';
   }
   prevBtn.style.color="royalblue";
   nextBtn.style.color="gray";
})

//
//
//        //MULTISLIDER
//        const multislider =  new Swiper('.category .swiper',{
//            direction : 'horizontal',
//            speed:100,
////            autoplay:{
////                delay:0,
////                disableOnInteraction: false,
////            },
//            loop : true,
//            slidesPerView :4,
//            centeredSlides : true,
//            pagination : {
//                el : '.swiper-pagination',
//                clickable :'true',
//                type:'bullets', // bullets,progressbar,fraction..
//            },
//            navigation : {
//                nextEl : '.swiper-button-next',
//                prevEl : '.swiper-button-prev',
//            },
//
//        });
//
//        //마우스 hover시 autoplay Stop
//        const swiperEl = document.querySelector('.wrapper>main>section.multi-slider>.swiper');
//        swiperEl.addEventListener('mouseover',function(){
//            multislider.autoplay.stop();
//        })
//        swiperEl.addEventListener('mouseleave',function(){
//            multislider.autoplay.start();
//        })
//        //centered Slider가 아닌 다른 slider 클릭시 해당 slider를 Center로 맞추기
//        const multislider_slideEls = document.querySelectorAll('.wrapper>main>section.multi-slider>.swiper>.swiper-wrapper>.swiper-slide');
//        multislider_slideEls.forEach(el=>{
//
//            el.addEventListener('click',function(e){
//                console.log(e.target);
//
//                multislider_slideEls.forEach(etcEl=>{
//                    etcEl.classList.remove('swiper-slide-active');
//                    etcEl.classList.remove('swiper-slide-next');
//                    etcEl.classList.remove('swiper-slide-prev');
//                })
//
//                el.classList.add('swiper-slide-active');
//            })
//
//        })