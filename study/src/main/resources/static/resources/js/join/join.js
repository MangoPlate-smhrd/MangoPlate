$(()=>{
    new Join();
})

export class Join {
    constructor() {
        console.log('login')
        this.eventBindgin();
    }

    eventBindgin() {
        $('.btn_join').on('click', (e)=> {
            // console.log("bbb");

            $('form').submit();
        })
    }
};