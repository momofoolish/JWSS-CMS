let key = "www.vjwss.top";
let getNowData = () => {
    let Dates = new Date();
    let Y = Dates.getFullYear();
    let M = Dates.getMonth() + 1;
    let D = Dates.getDate();
    let H = Dates.getHours();
    let t = Y + (M < 10 ? "/0" : "/") + M + (D < 10 ? "/0" : "/") + D + " " + (H < 10 ? "0" : "") + H;
    let k = "register" + key + t;
    return md5(k);
}

class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {account: '', password: '', verifyCode: '', tipAcc: '', tipPas: '', tipPaa: ''};
    }

    //提交注册按钮
    onClickToRegister = () => {
        let {account, password, verifyCode} = this.state;
        if (account === '' || account.length < 6 || account.length > 16) {
            alert("账号必须在6~16间");
            return;
        }
        if (password === '' || password.length < 6 || password.length > 16) {
            alert("密码必须在6~16间");
            return;
        }
        //发起http请求
        this.httpLogin(account, password, verifyCode);
    }

    //注册表单内容提交
    httpLogin = (a, p, v) => {
        console.log(a, p, v);
        let url = "/api/login/formLogin";
        let formData = {
            account: a,
            password: p,
            code: v,
            key: getNowData()
        }
        $.ajax({
            url: url,
            method: 'post',
            data: formData,
            success: function (response) {
                console.log(response);
                if (response.code === 1) {
                    console.log("登录成功");
                    window.location.href = "/";
                } else {
                    alert("登录失败,账号或密码错误");
                }
            }
        });
    }

    onAccountChange = (e) => {
        let acc = e.target.value;
        this.setState({account: acc, tipAcc: '你输入了账号'});
    }

    onPasswordChange = (e) => {
        let pas = e.target.value;
        this.setState({password: pas, tipPas: '你输入了密码'});
    }

    onVerifyCodeChange = (e) => {
        let ver = e.target.value;
        this.setState({verifyCode: ver});
    }

    render() {
        const {tipAcc, tipPas} = this.state;
        return (
            <div className="containerForm">
                <div className="card shadow p-3 mb-5 bg-white rounded">
                    <div style={{height: '100%'}}>
                        <p>欢迎━(*｀∀´*)ノ亻!</p>
                        <p>{tipAcc === '' ? '' : tipAcc}</p>
                        <p>{tipPas === '' ? '' : tipPas}</p>
                    </div>
                    <a href="/register">前往注册-></a>
                </div>

                <div className="card shadow p-3 mb-5 bg-white rounded cardStyle">
                    <label style={{marginTop: '1em', paddingLeft: '6px', paddingRight: '1px'}}>
                        <Input height={'3em'} width={'100%'} placeholder={'输入账号'} onChange={this.onAccountChange}/>
                    </label>
                    <label style={{marginTop: '1em', paddingLeft: '6px', paddingRight: '1px'}}>
                        <Input height={'3em'} width={'100%'} placeholder={'输入密码'} onChange={this.onPasswordChange}
                               type={'password'}/>
                    </label>
                    <label style={{marginTop: '1em', paddingLeft: '6px', paddingRight: '1px'}}>
                        <Input height={'3em'} width={'50%'} placeholder={'输入验证码'} onChange={this.onVerifyCodeChange}/>
                        <img src={'/api/register/verifyCode'} style={{width: '50%'}}
                             alt="验证码加载ing"/>
                    </label>
                    <Button value="登录" height={'3em'} onClick={this.onClickToRegister}/>
                </div>
            </div>
        )
    }
}
