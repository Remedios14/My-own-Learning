"""
Solving FrozenLake environment using Policy-Iteration.

Adapted by Bolei Zhou for IERG6130. Originally from Moustafa Alzantot (malzantot@ucla.edu)
"""
import numpy as np
import gym
from gym import wrappers
from gym.envs.registration import register

def run_episode(env, policy, gamma = 1.0, render = False):
    """ Runs an episode and return the total reward """
    obs = env.reset()
    total_reward = 0
    step_idx = 0
    while True:
        if render:
            env.render()
        obs, reward, done , _ = env.step(int(policy[obs]))
        total_reward += (gamma ** step_idx * reward)
        step_idx += 1
        if done:
            break
    return total_reward


def evaluate_policy(env, policy, gamma = 1.0, n = 100):
    # 重复执行 n 次
    scores = [run_episode(env, policy, gamma, False) for _ in range(n)]
    return np.mean(scores)

def extract_policy(v, gamma = 1.0):
    """ Extract the policy given a value-function """
    policy = np.zeros(env.env.nS)
    for s in range(env.env.nS):
        q_sa = np.zeros(env.env.nA)
        for a in range(env.env.nA):
            """遍历所有动作，env.env.P[s][a] 表示状态 s 下执行 a 动作概率，
                  (s,a) 后的转移状态 s' ，该转移对应的奖励 r
            """
            q_sa[a] = sum([p * (r + gamma * v[s_]) for p, s_, r, _ in  env.env.P[s][a]])
        policy[s] = np.argmax(q_sa)
    return policy

def compute_policy_v(env, policy, gamma=1.0):
    """ Iteratively evaluate the value-function under policy.
    Alternatively, we could formulate a set of linear equations in iterms of v[s] 
    and solve them to find the value function.
    """
    v = np.zeros(env.env.nS)
    eps = 1e-10
    while True:
        prev_v = np.copy(v)
        for s in range(env.env.nS):
            policy_a = policy[s]
            v[s] = sum([p * (r + gamma * prev_v[s_]) for p, s_, r, _ in env.env.P[s][policy_a]])
        if (np.sum((np.fabs(prev_v - v))) <= eps):
            # value converged
            break
    return v

def policy_iteration(env, gamma = 1.0):
    """ Policy-Iteration algorithm """
    # 从所有动作空间随机抽样 s 个，作为策略，返回长度为 nS 的数组
    policy = np.random.choice(env.env.nA, size=(env.env.nS))  # initialize a random policy
    max_iterations = 200000
    gamma = 1.0
    for i in range(max_iterations):
        # 以旧策略计算所有状态的 value
        old_policy_v = compute_policy_v(env, policy, gamma)
        # 在 value 下贪心地选取此时的最优策略进入下一轮
        new_policy = extract_policy(old_policy_v, gamma)
        if (np.all(policy == new_policy)): # 策略不再变化则提前停止
            print ('Policy-Iteration converged at step %d.' %(i+1))
            break
        policy = new_policy
    return policy

if __name__ == '__main__':

    env_name  = 'FrozenLake-v0' # 'FrozenLake8x8-v0'
    env = gym.make(env_name)

    optimal_policy = policy_iteration(env, gamma = 1.0)
    scores = evaluate_policy(env, optimal_policy, gamma = 1.0)
    print('Average scores = ', np.mean(scores))