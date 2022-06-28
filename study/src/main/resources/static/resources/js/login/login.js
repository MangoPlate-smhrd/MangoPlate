$(()=>{
    new Login();
})

export class Login {
    constructor() {
        console.log('login')
        this.eventBindgin();
    }

    eventBindgin() {
        $('.btn_login').on('click', (e)=> {
            // console.log("bbb");

            $('form').submit();
        })
    }
};