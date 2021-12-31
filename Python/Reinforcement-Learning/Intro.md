# yosoro~

一个 agent 如何在环境中输出状态和 action 最大化总体 reward

**主要难点** ：

- 输入数据为前后**相关**的连续序列（非独立性）；
- 执行 action 并不能立即得到反馈；
- 需要一定的 exploration （类似监督学习中跳出局部最优）

**主要设计元素**：

- 奖励：

  - 通常如完成周期的成败奖励（意味着在周期内的行为都没有此处的反馈）
  - 部分设计的状态奖励（评价取得的优势等？）
  - 奖励信号可以是状态和所采取的行动的随机函数

- 序列决策:

  - 在完成一个周期获得奖励反馈之前持续给出决策序列
  - 历史数据包括 观测，动作和奖励

  $$
  H_t = O_1, R_1, A_1, ... , A_{t-1}, O_t, R_t
  $$

  - 通常由历史信息确定状态，分为环境状态和智能体状态

  $$
  S^e_t = f^e(H_t) \space\space\space S^a_t = f^a(H_t)
  $$

**智能体构成**：

- 策略 Policy：智能体的行为函数 （状态到行为），目标在于最大化长期的奖励总和
- 价值函数：每个状态或动作的价值，是对未来奖励的当前折扣期望，是构成策略的主要考虑要素
- 模型：由环境变量表示 agent 的状态，能根据当前的 state 和 action 推断出下一时刻的状态

**Policy** 包括：

1. Stochastic policy : 
   $$
   \pi(a|s) = P[A_t = a|S_t = s]
   $$

2. Deterministic policy :
   $$
   a^* = arg\space max_a\pi(a|s)
   $$

**Value function** 将未来奖励折扣到当下用于评价
$$
v_\pi(s)\hat{=}\mathbb{E}_\pi[G_t|S_t=s] = \mathbb{E}_\pi[\sum_{k=0}^\infty \gamma^k R_{t+k+1}|S_t=s] \space , \space for\space all\space s \in \mathsf S
$$
或者 Q-function
$$
v_\pi(s)\hat{=}\mathbb{E}_\pi[G_t|S_t=s,A_t=a] = \mathbb{E}_\pi[\sum_{k=0}^\infty \gamma^k R_{t+k+1}|S_t=s,A_t = a]
$$
此处 Gt 即为奖励的折扣和

**Model** 用于预测环境的变化

Predict the next state : 
$$
\mathtt P^a_{ss'}=\mathbb P[S_{t+1}=s'|S_t=s,A_t,a]
$$
Predict the next reward :
$$
\mathtt R^a_s = \mathbb E[R_{t+1}|S_t=s,A_t=a]
$$
