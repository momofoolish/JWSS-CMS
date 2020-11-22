import React, { Fragment, useState } from 'react';
import './paging.css';

// 导出组件
function Paging(props) {
    const [current, setCurrent] = useState(1);

    const { total, limit } = props;
    var totalPage = Math.floor(total / limit);

    // 上一页
    const previousPage = () => {
        setCurrent(current - 1);
        props.onPageChange(current);
    }

    // 下一页
    const nextPage = () => {
        setCurrent(current + 1);
        props.onPageChange(current);
    }

    //改变页
    const changeCurrent = (event) => {
        var v = Math.floor(event.target.value);
        setCurrent(v);
        props.onPageChange(current);
    }

    return (
        <ul className="com-public-paging">
            <Next current={current} limit={1} value="<"
                nextPage={previousPage}
            />

            <Page total={totalPage} current={current}
                changeCurrent={changeCurrent}
            />

            <Next current={current} limit={totalPage} value=">"
                nextPage={nextPage}
            />
        </ul>
    )
}

//渲染页码
function Page(props) {
    const doms = [];
    if (props.total === 0) {
        doms.push(
            <input type="button" value={1} key={1}
                disabled="disabled"
                onClick={props.changeCurrent}
            />
        )
    }
    for (var i = 1; i <= props.total; i++) {
        doms.push(
            <input type="button" value={i} key={i}
                className={
                    props.current === i ? 'page-selected' : 'page-no-select'
                }
                onClick={props.changeCurrent}
            />
        )
    }
    return doms;
}

// 切换上一页和下一页
function Next(props) {
    const { current, limit, value } = props;
    return (
        <Fragment>
            <li>
                <input type="button" value={value}
                    className="page-no-select"
                    disabled={
                        value === '>' ?
                            current >= limit ? "disabled" : ''
                            :
                            current <= limit ? "disabled" : ''
                    }
                    onClick={props.nextPage}
                />
            </li>
        </Fragment>
    )
}

export default Paging;